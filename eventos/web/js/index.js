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
        var reload = false;

        // Se tem query string, força reload da pagina,
        // senão não atualiza os dados.
        if (window.location.hash.indexOf("?") > -1) {
            reload = true;
        }
        
        window.location.hash = e.target.hash;

        if (reload) {
            window.location.reload();
        }
    });

    $(".datetime").datetimepicker({
        //language: 'pt-BR' // esta exibindo errada a mascara.
    });
});