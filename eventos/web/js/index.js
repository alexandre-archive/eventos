var forceRealod = true;

$(document).ready(function() {

    var tab = getTab();

    if (tab) {
        $('#tabMenu a[href=#' + tab + ']').tab('show');
    } else {
        $('#tabMenu a:first').tab('show');
    }

    if (tab === "new") {
        $('.jumbotron').css('width', '60%').css('margin-left', '20%');
    } else {
        $('.jumbotron').css('width', '100%').css('margin-left', '0');
    }

    $(window).on('hashchange', function() {
        // UHG!!!
        // Force reload to show correct content.
        if (forceRealod) {
            window.location.reload();
        } else {
            forceRealod = true;
        }
    });

    /* Para que ao apertar F5 não perder a tab atual. */
    $('#tabMenu a').on('click', function(e) {
        forceRealod = window.location.hash !== e.target.hash;
        window.location.hash = e.target.hash;
    });

    $("#initTime, #endTime").datetimepicker({
        language: 'pt-BR'
    });

    $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
        var tab = getTab();

        if (tab === "new") {
            $('.jumbotron').css('width', '60%').css('margin-left', '20%');
        } else {
            $('.jumbotron').css('width', '100%').css('margin-left', '0');
        }
    });
});