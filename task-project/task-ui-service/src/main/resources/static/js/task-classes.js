$(document).ready(function () {
    function fillTable(classes) {
        $("#classes > tbody").html("");
        let $tableBody = $("#classes tbody");
        classes.forEach(function (item, index) {
            let now = new Date();
            let created = new Date(item.created);
            let deadline = new Date(item.deadline);
            let the_day_before_deadline = new Date(item.deadline);
            the_day_before_deadline.setDate(deadline.getDate() - 1);
            the_day_before_deadline.setHours(0);
            the_day_before_deadline.setMinutes(0);

            let $line;

            let status = item.status.toLowerCase();
            if ((index + 1) % 2)
                $line = $(`<tr id='${index}' class='odd' data-status='${status}'>`);
            else
                $line = $(`<tr id='${index}' class='even' data-status='${status}'>`);


            $line.append($("<td class='name'>").text(item.name));
            $line.append($("<td class='description'>").text(item.description));

            if (now > deadline)
                $line.append($("<td class='deadline deadline-overdue'>").text(dateToString(deadline)));
            else if (now >= the_day_before_deadline)
                $line.append($("<td class='deadline deadline-almost-overdue'>").text(dateToString(deadline)));
            else
                $line.append($("<td class='deadline deadline-normal'>").text(dateToString(deadline)));

            $line.append($("<td class='creation-time'>").text(dateToString(created)));
            $line.append($("<td class='status status-created'>").text(item.status));

            if (item.priority === "HIGH")
                $line.append($("<td class='priority priority-high'>").text(item.priority));
            else if (item.priority === "MEDIUM")
                $line.append($("<td class='priority priority-medium'>").text(item.priority));
            else
                $line.append($("<td class='priority priority-low'>").text(item.priority));


            $line.append($(`<td class='delete-td'><input type='image' class='delete-btn' alt='Delete' 
                            src='../assets/delete.png' id='${index}'
                            onclick='deleteTask(document.getElementById(${index}), this.value)' value='${item.id}'>`));
            $line.append($(`<td class='edit-td'><button id='${index}' class="btn btn-success btn-edit" value='${item.id}' 
                            onclick="editTask(${index}, this.value)">Edit</button>`));

            $tableBody.append($line);
        });
    }

    $.getJSON("/classes", function (classes) {
        fillTable(classes)
    });


    $('#sorting').change(function () {
        $.getJSON("/classes/" + $(this).val(), function (classes) {
            fillTable(classes)
        });
    });

    $('.btn-filter').on('click', function () {
        var $target = $(this).data('target');
        if ($target !== 'all') {
            $('.table > tbody tr').css('display', 'none');
            $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
        } else {
            $('.table > tbody tr').css('display', 'none').fadeIn('slow');
        }
    });
});

function deleteTask(task, id) {
    task.style.display = "none";
    $.ajax({
        url: `/tasks/delete-task/${id}`,
        type: 'DELETE',
    });
}

function editTask(tr_id, id){
    $(`tr#${tr_id}`).addClass("on-edit");
    setTaskNameTextArea(tr_id);
    setTaskDescriptionTextArea(tr_id);
    setTaskDeadlineInput(tr_id);
    setTaskStatusOptions(tr_id);
    setTaskPriorityOptions(tr_id)
    changeEditButtonToAccept(tr_id, id);
}

function changeEditButtonToAccept(tr_id, id){
    let $button = $(`tr#${tr_id} button.btn-edit`);
    $button.removeClass("btn-edit");
    $button.addClass("btn-accept");
    $button.text("Accept");
    $button.attr("onclick","saveChanges(this.id, this.value)");
}

function saveChanges(tr_id, id){
    let $button = $(`tr#${tr_id} button.btn-accept`)
    let newTaskName = $(`tr#${tr_id} td.name textarea`).val();
    let newTaskDescription = $(`tr#${tr_id} td.description textarea`).val();
    let newTaskDeadline = $(`tr#${tr_id} td.deadline input`).val();
    let newTaskStatus = $(`tr#${tr_id} td.status select option:selected`).val();
    let newTaskPriority = $(`tr#${tr_id} td.priority select option:selected`).val();
    let editedTask = {};
    editedTask.name = newTaskName;
    editedTask.description = newTaskDescription;
    editedTask.deadline = newTaskDeadline;
    editedTask.created =  getDateInDatetimeLocalFormat($(`tr#${tr_id} td.creation-time`).text());
    editedTask.status = newTaskStatus;
    editedTask.priority = newTaskPriority;
    showChangesInTable(editedTask, tr_id);
    changeAcceptButtonToEdit($button);
    $(`tr#${tr_id}`).removeClass("on-edit");
    sendChangesToServer(editedTask, id);
}

function sendChangesToServer(task, id){
    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: `/tasks/edit-task/${id}`,
        data: JSON.stringify(task),
        error: function () {
            alert('Error occurred');
        }
    });
}

function showChangesInTable(task, tr_id){
    $(`tr#${tr_id} td.name textarea`).remove();
    $(`tr#${tr_id} td.name`).text(task.name);
    $(`tr#${tr_id} td.description textarea`).remove();
    $(`tr#${tr_id} td.description`).text(task.description);
    $(`tr#${tr_id} td.deadline input`).remove();
    $(`tr#${tr_id} td.deadline`).text(dateToString(new Date(task.deadline)));
    $(`tr#${tr_id} td.status select`).remove();
    $(`tr#${tr_id} td.status`).text(task.status);
    $(`tr#${tr_id} td.priority select`).remove();
    $(`tr#${tr_id} td.priority`).text(task.priority);
}

function changeAcceptButtonToEdit($button){
    $button.removeClass("btn-accept");
    $button.addClass("btn-edit");
    $button.text("Edit");
    $button.attr("onclick","editTask(this.id, this.value)");
}


function setTaskNameTextArea(tr_id){
    let $taskNameTd = $(`tr#${tr_id} td.name`);
    let taskName = $taskNameTd.text();
    $taskNameTd.empty();
    let $textArea = $(`<textarea id="edit-task-name">`);
    $textArea.val(taskName);
    $taskNameTd.append($textArea);
}

function setTaskDescriptionTextArea(tr_id){
    let $taskDescriptionTd = $(`tr#${tr_id} td.description`);
    let taskDescription = $taskDescriptionTd.text();
    $taskDescriptionTd.empty();
    let $textArea = $(`<textarea id="edit-task-description">`);
    $textArea.val(taskDescription);
    $taskDescriptionTd.append($textArea);
}

function getDateInDatetimeLocalFormat(date){
    let dateParts = date.split(" ");
    let dateMonthDayYear = dateParts[0];
    let dateHourMinute = dateParts[1];
    let dateMonthDayYearArr = dateMonthDayYear.split('/');
    let dateHourMinuteArr = dateHourMinute.split(':');
    let year = dateMonthDayYearArr[2];
    let month = dateMonthDayYearArr[0];
    let day = dateMonthDayYearArr[1];
    let hour = dateHourMinuteArr[0];
    let minute = dateHourMinuteArr[1];
    return  year+"-"+month+"-"+day+"T"+hour+":"+minute;
}

function setTaskDeadlineInput(tr_id){
    let $taskDeadlineTd = $(`tr#${tr_id} td.deadline`);
    let taskDeadlineStr = $taskDeadlineTd.text();
    let dateInDatetimeLocalFormat = getDateInDatetimeLocalFormat(taskDeadlineStr);
    $taskDeadlineTd.empty();
    let $inputDatetimeLocal = $(`<input type="datetime-local" id="edit-task-deadline"/>`);
    $inputDatetimeLocal.val(dateInDatetimeLocalFormat);
    $taskDeadlineTd.append($inputDatetimeLocal);
}

function appendStatusOptions($selectOptions, status){
    let $optionCreated, $optionInProgress, $optionDone;
    switch (status){
        case "CREATED":
            $optionCreated = $('<option id="edit-task-status-created" selected>');
            $optionInProgress = $('<option id="edit-task-status-progress">');
            $optionDone = $('<option id="edit-task-status-done">');
            break;
        case "IN_PROGRESS":
            $optionCreated = $('<option id="edit-task-status-created">');
            $optionInProgress = $('<option id="edit-task-status-progress" selected>');
            $optionDone = $('<option id="edit-task-status-done">');
            break;
        case "DONE":
            $optionCreated = $('<option id="edit-task-status-created" >');
            $optionInProgress = $('<option id="edit-task-status-progress">');
            $optionDone = $('<option id="edit-task-status-done" selected>');
            break;
        default:
            $optionCreated = $('<option id="edit-task-status-created">');
            $optionInProgress = $('<option id="edit-task-status-progress">');
            $optionDone = $('<option id="edit-task-status-done">');
    }
    $optionCreated.val("CREATED").text("CREATED");
    $optionInProgress.val("IN_PROGRESS").text("IN_PROGRESS");
    $optionDone.val("DONE").text("DONE");
    $selectOptions.append($optionCreated);
    $selectOptions.append($optionInProgress);
    $selectOptions.append($optionDone);
}

function setTaskStatusOptions(tr_id){
    let $taskStatusTd = $(`tr#${tr_id} td.status`);
    let taskStatus = $taskStatusTd.text();
    $taskStatusTd.empty();
    let $selectOptions = $(`<select class="form-select form-select-lg" aria-label=".form-select-lg example" ` +
        `id="edit-task-status-select">`);
    appendStatusOptions($selectOptions, taskStatus);
    $taskStatusTd.append($selectOptions);
}

function appendPriorityOptions($selectOptions, priority){
    let $optionHigh, $optionMedium, $optionLow;
    switch (priority){
        case "HIGH":
            $optionHigh = $('<option id="edit-task-priority-high" selected>');
            $optionMedium = $('<option id="edit-task-priority-medium">');
            $optionLow = $('<option id="edit-task-priority-low">');
            break;
        case "MEDIUM":
            $optionHigh = $('<option id="edit-task-priority-high">');
            $optionMedium = $('<option id="edit-task-priority-medium" selected>');
            $optionLow = $('<option id="edit-task-priority-low">');
            break;
        case "LOW":
            $optionHigh = $('<option id="edit-task-priority-high" >');
            $optionMedium = $('<option id="edit-task-priority-medium">');
            $optionLow = $('<option id="edit-task-priority-low" selected>');
            break;
        default:
            $optionHigh = $('<option id="edit-task-priority-high">');
            $optionMedium = $('<option id="edit-task-priority-medium">');
            $optionLow = $('<option id="edit-task-priority-low">');
    }
    $optionHigh.val("HIGH").text("HIGH");
    $optionMedium.val("MEDIUM").text("MEDIUM");
    $optionLow.val("LOW").text("LOW");
    $selectOptions.append($optionHigh);
    $selectOptions.append($optionMedium);
    $selectOptions.append($optionLow);
}


function setTaskPriorityOptions(tr_id){
    let $taskPriorityTd = $(`tr#${tr_id} td.priority`);
    let taskPriority = $taskPriorityTd.text();
    $taskPriorityTd.empty();
    let $selectOptions = $(`<select class="form-select form-select-lg" aria-label=".form-select-lg example" ` +
        `id="edit-task-priority-select">`);
    appendPriorityOptions($selectOptions, taskPriority);
    $taskPriorityTd.append($selectOptions);
}

function dateToString(date) {
    let year = date.getFullYear();

    let month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;

    let day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;

    let hour = (date.getHours() < 10 ? '0' : '') + date.getHours();

    let minute = (date.getMinutes() < 10 ? '0' : '') + date.getMinutes();

    return month + "/" + day + "/" + year + " " + hour + ":" + minute;
}



