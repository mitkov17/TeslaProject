;$(function(){
    var init = function() {
        initBuyBtn();
        $('#addToCart').click(addProductToCart);
        $('#addProductPopup .count').change(calculateCost);
        $('#loadMore').click(loadMoreProducts);
        $('#goSearch').click(goSearch);
        $('.remove-product').click(removeProductFromCart);
    };

    var showAddProductPopup = function () {
        var idProduct = $(this).attr('data-id-product');
        var product = $('#product' + idProduct);
        $('#addProductPopup').attr('data-id-product', idProduct);
        $('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
        $('#addProductPopup .name').text(product.find('.nameTr').text());
        var price = product.find('.pricePark').text();

        $('#addProductPopup .pricePark').text(price);

        $('#addProductPopup .brandPark').text(product.find('.brandPark').text());
        $('#addProductPopup .modelPark').text(product.find('.modelPark').text());
        $('#addProductPopup .materialPark').text(product.find('.materialPark').text());
        $('#addProductPopup .lengthPark').text(product.find('.lengthPark').text());
        $('#addProductPopup .count').val(1);
        $('#addProductPopup .costPark').text(price);

        $('#addProductPopup').modal({
            show:true
        });
    };

    var initBuyBtn = function() {
        $('.buy-btn').click(showAddProductPopup)
    }

    var adTransportToCart = function() {
        var idTransport = $('#addTransportPopup').attr('data-id-product');
        $('#addToCart').addClass('hidden');
        $('#addToCartIndicator').removeClass('hidden');
        
        setTimeout(function() {
            $('#addTransportPopup').modal('hide');
            $('#addToCartIndicator').addClass('hidden');
            $('#addToCart').removeClass('hidden');
        }, 800);
    }

    var calculateCost = function() {
        var priceStr = $('#addProductPopup .pricePark').text();
        var price = parseFloat(priceStr.replace('$', ' '));
        var count = parseInt( $('#addProductPopup .count').val());
        var min = parseInt( $('#addProductPopup .count').attr('min'));
        var max = parseInt( $('#addProductPopup .count').attr('max'));

        if(count >= min && count <= max) {
            var cost = price * count;
            $('#addProductPopup .costPark').text('$ ' + cost);
        } else {
            $('#addProductPopup .count').val(1);
            $('#addProductPopup .costPark').text(priceStr)
        }
    }

    var loadMoreProducts = function() {
        $('#loadMore').addClass('hidden');
        $('#loadMoreIndicator').removeClass('hidden');

        setTimeout(function() {
            $('#loadMoreIndicator').addClass('hidden');
            $('#loadMore').removeClass('hidden');
        }, 800);
    }

    /*var adTransportToCart = function() {
        var count = $('#addTransportPopup .count').val();
        //var cost = $('#addTransportPopup .costPark').val();
        var cost = 2000;
        $('#addToCart').addClass('hidden');
        $('#addToCartIndicator').removeClass('hidden');
        setTimeout(function() {
            $('.total-count').text(count);
            $('.total-cost').text(cost);
            $('#addTransportPopup').modal('hide');
            $('#addToCartIndicator').addClass('hidden');
            $('#addToCart').removeClass('hidden');
        }, 800)
    };*/

    var addProductToCart = function() {
        var count = $('#addProductPopup .count').val();
        //var cost = $('#addTransportPopup .costPark').val();
        //var cost = 2000;
        $('#addToCart').addClass('hidden');
        $('#addToCartIndicator').removeClass('hidden');
        setTimeout(function() {
            var data = {
                totalCount : count,
                totalCost : 270
            };
            $('.total-count').text(data.totalCount);
            $('.total-cost').text(data.totalCost);
            $('#addProductPopup').modal('hide');
            $('#addToCartIndicator').addClass('hidden');
            $('#addToCart').removeClass('hidden');
        }, 800)
    };

    var goSearch = function() {
        $('form.search').submit();
    };

    var confirm = function(msg, okFunction) {
        if (window.confirm(msg)) {
            okFunction();
        }
    };

    var removeProductFromCart = function() {
        var btn = $(this);
        confirm('Are you sure?', function() {
            executeRemoveProduct(btn);
        });
    };

    var refreshTotalCost = function() {
        var total = 0;
        $('#shoppingCart .item').each(function(index, value) {
            var count = parseInt($(value).find('.count').text());
            var price = parseFloat($(value).find('.price').text().replace('$', ' '));
            total += price * count;
        });
        $('#shoppingCart .total').text('$' + total);
    };

    var executeRemoveProduct = function(btn) {
        var idProduct = btn.attr('data-id-product');
        var count = btn.attr('data-count');
        btn.removeClass('btn-danger');
        btn.removeClass('btn');
        btn.addClass('load-indicator')
        var text = btn.text();
        btn.text('');
        btn.off('click');

        setTimeout(function() {
            var data = {
                totalCount : 1,
                totalCost : 1
            };

            if(data.totalCount === 0) {
                window.location.href = 'carPark.html';
            } else {
                var prevCount = parseInt($('#product' + idProduct + ' .count').text())
                var remCount = parseInt(count);
                if (remCount === prevCount) {
                    $('#product' + idProduct).remove();
                    if ($('#shoppingCart .item').length === 0) {
                        window.location.href = 'carPark.html';
                    }
                } else {
                    btn.removeClass('load-indicator')
                    btn.addClass('btn-danger');
                    btn.addClass('btn');
                    btn.text(text);
                    btn.click(removeProductFromCart);
                    $('#product' + idProduct + ' .count').text(prevCount - remCount);
                    if (prevCount - remCount == 1) {
                        $('#product' + idProduct + ' a.remove-product.all').remove();
                    }
                }
                refreshTotalCost();
            }
        }, 1000);
    };

    init();
});