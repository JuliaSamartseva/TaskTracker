$(document).ready(function () {

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

    function deleteTask(task, id) {
        task.style.display = "none";
        $.ajax({
            url: `/tasks/delete-task/${id}`,
            type: 'DELETE',
        });
        $.ajax({
            type: 'GET',
            url: `/statistics/deleted_tasks`,
        });
    }

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
                            src='../assets/delete.png' width='20px' height='auto' 
                            onclick='deleteTask(document.getElementById(${index}), this.value)' value='${item.id}'>`));

            $tableBody.append($line);
        })
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