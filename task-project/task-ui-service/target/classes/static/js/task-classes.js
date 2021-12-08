function dateToString(date){
    let year = date.getFullYear();

    let month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;

    let day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;

    let hour = (date.getHours()<10?'0':'') + date.getHours();

    let minute = (date.getMinutes()<10?'0':'') + date.getMinutes();

    return month+"/"+day+"/"+year+" "+hour+":"+minute;
}



$.getJSON("/classes", function (classes) {
    let $tableBody = $("#classes tbody");
    classes.forEach(function (item) {
        let now = new Date();
        let created = new Date(item.created);
        let deadline = new Date(item.deadline);

        let $line = $("<tr>");
        $line.append($("<td>").text(item.name));
        $line.append($("<td>").text(item.description));
        // if(time<=Date.now())
        //     $line.append($("<td class='ui-icon-calendar ui-state-error'>").text(item.deadline));
        // else if((time.getDate()-2)<=Date.now())
        //     $line.append($("<td class='ui-icon-calendar ui-state-active'>").text(item.deadline));
        // else {}
        $line.append($("<td class='ui-icon-calendar'>").text(dateToString(deadline)));
        $line.append($("<td class='ui-icon-calendar'>").text(dateToString(created)));
        $line.append($("<td class='status-created'>").text(item.status));
        if(item.priority==="HIGH")
            $line.append($("<td class='ui-state-highlight'>").text(item.priority));
        else
            $line.append($("<td>").text(item.priority));
        $tableBody.append($line);
    })
});
