function raiseTab() {
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
}

$(document).ready(function() {

    raiseTab();

    $(window).on('hashchange', function() {
        raiseTab();
    });

    /* Para que ao apertar F5 não perder a tab atual. */
    $('#tabMenu a').on('click', function(e) {
        window.location.hash = e.target.hash;
    });

    $("#initTime, #endTime").datetimepicker({
        language: 'pt-BR'
    });
});