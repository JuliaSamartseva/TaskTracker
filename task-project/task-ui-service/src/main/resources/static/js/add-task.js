$(function() {
    $("form[id='addTask']").validate({
        rules: {
            name: "required",
            description: "required",
            priority: "required",
            deadline: "required"
        },
        messages: {
            firstname: "Please enter task name",
            description: "Please enter task description",
            priority: "Please choose priority",
            deadline: "Please choose a deadline"
        }
    });
});

$("#addTask").submit(function(e) {
    e.preventDefault();
    let form = $(this);
    if (form.valid()) {
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
            success: function() {
                window.location.href = "/";
            },
            error: function() {
                alert('Error occurred');
            }
        });
    }
});
