$("#addTask").submit(function(e) {
    e.preventDefault();
    let form = $(this);
    let url = form.attr('action');
    var config = {};
    jQuery(form).serializeArray().map(function(item) {
        if ( config[item.name] ) {
            if ( typeof(config[item.name]) === "string" ) {
                config[item.name] = [config[item.name]];
            }
            config[item.name].push(item.value);
        } else {
            config[item.name] = item.value;
        }
    });

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: url,
        data: JSON.stringify(config),
        dataType: 'json'
    });
});
