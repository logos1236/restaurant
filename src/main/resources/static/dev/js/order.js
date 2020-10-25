jQuery(document).ready(function () {
//=== Create order
    $("body").on("click", ".service-order_create", function () {
        var form_data = new Object();
        var _this_button = $(this);
        var _this_preloader = $(this);

        form_data.is_email_push = $("#auth_checkbox_email:checked").length;
        form_data.email = $("#order_email").val();

        if (_this_button.hasClass("no_click")) {
            return false;
        }

        $.ajax({
            url: "/ajax/order_create.php",
            method: 'post',
            dataType: 'json',
            data: form_data,
            beforeSend: function () {
                _this_button.addClass("no_click");
                _this_preloader.addClass("loading");
            },
            success: function (response) {
                if (typeof (response) != 'undefined' && response != null) {
                    if (response.success > 0) {
                        toastrPrintList(response.success_message, 'success');
                    } else {
                        toastrPrintList(response.error, 'error');
                    }

                    if (typeof (response.url_redirect) != 'undefined' && response.url_redirect != null) {
                        window.location = response.url_redirect;
                    }
                }
            },
            error: function () {
                toastr.error(window.toastr_error);
            },
            complete: function () {
                _this_button.removeClass('no_click');
                _this_preloader.removeClass('loading');
            }
        });

        return false;
    });
    
//=== Filter order on user lk page
    /*$("body").on("change", ".service-order-filter", function(){          
        var _this_form = $(this).closest("form");
        _this_form.submit();
        
        return false;
    });*/
    
//=== Repeat order
    /*$("body").on("click", ".service-repeat_order", function () {     
        $(this).closest("form").submit();
        return false;
    });
    $("body").on("submit", ".service-repeat_order_form", function () {
        var _this_button = $(this).find(".service-repeat_order");
        var _this_preloader = $(this).find(".service-repeat_order");
        var _this_form = $(this);
        var phorm_data = _this_form.serialize();

        if (_this_button.hasClass("no_click")) {
            return false;
        }

        $.ajax({
            url: _this_form.attr("action"),
            type: _this_form.attr("method"),
            data: phorm_data,
            beforeSend: function () {
                _this_button.addClass("no_click");
                _this_preloader.addClass("loading");
            },
            success: function (response) {
                updateCartView(response);
                
                if (response.repeat_order.success > 0) {
                    toastrPrintList(response.repeat_order.success_message, 'success');
                } else {
                    toastrPrintList(response.repeat_order.error, 'error');
                }
            },
            error: function () {
                toastr.error(window.toastr_error);
            },
            complete: function () {
                _this_button.removeClass('no_click');
                _this_preloader.removeClass('loading');
            }
        });

        return false;
    });*/  
    
//=== Cancel order
    $("body").on("click", ".service-cancel_order", function () {     
        $(this).closest("form").submit();
        return false;
    });
    $("body").on("submit", ".service-cancel_order_form", function () {
        var _this_button = $(this).find(".service-cancel_order");
        var _this_preloader = $(this).find(".service-cancel_order");
        var _this_form = $(this);
        var phorm_data = _this_form.serialize();
        
        var order_item = _this_form.closest('.service-order_item');
        var status_block = order_item.find(".thisStat");
        var order_icon = order_item.find(".order-icon");

        if (_this_button.hasClass("no_click")) {
            return false;
        }

        $.ajax({
            url: _this_form.attr("action"),
            type: _this_form.attr("method"),
            data: phorm_data,
            beforeSend: function () {
                _this_button.addClass("no_click");
                _this_preloader.addClass("loading");
            },
            success: function (response) {
                if (typeof (response) != 'undefined' && response != null) {
                    if (response.success > 0) {
                        toastrPrintList(response.success_message, 'success');
                        
                        window.location.reload();
                    } else {
                        toastrPrintList(response.error, 'error');
                    }
                }
            },
            error: function () {
                toastr.error(window.toastr_error);
            },
            complete: function () {
                _this_button.removeClass('no_click');
                _this_preloader.removeClass('loading');
            }
        });

        return false;
    });
});
