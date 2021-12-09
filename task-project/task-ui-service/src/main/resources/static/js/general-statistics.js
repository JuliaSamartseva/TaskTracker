$.getJSON("/get-statistics", function (statistics) {
    $('#created').text(statistics.totalTasksCreated);
    $('#deleted').text(statistics.totalTasksDeleted);
    $('#total').text(statistics.totalTasksDone);
    $('#by_deadline').text(statistics.totalTasksDoneByDeadline);
    $('#over_deadline').text(statistics.totalTasksDoneOverDeadline);
});