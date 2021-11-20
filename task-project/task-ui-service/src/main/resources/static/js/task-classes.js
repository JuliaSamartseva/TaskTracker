$.getJSON("/classes", function (classes) {
    let $tableBody = $("#classes tbody");
    classes.forEach(function (item) {
        let $line = $("<tr>");
        $line.append( $("<td>").text(item.courseName) );
        $line.append( $("<td>").text(item.teacherName) );
        $line.append( $("<td>").text(item.year) );
        $line.append( $("<td>").text(item.numberOfStudents) );
        $tableBody.append($line);
    })
});