$.getJSON("/classes", function (classes) {
    let $tableBody = $("#classes tbody");
    classes.forEach(function (item) {
        let $line = $("<tr>");
        $line.append($("<td>").text(item.name));
        $line.append($("<td>").text(item.description));
        $line.append($("<td>").text(item.deadline));
        $line.append($("<td>").text(item.created));
        $line.append($("<td>").text(item.status));
        $line.append($("<td>").text(item.priority));
        $tableBody.append($line);
    })
});
