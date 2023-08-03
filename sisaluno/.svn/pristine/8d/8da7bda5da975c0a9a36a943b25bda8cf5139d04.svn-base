var SISCOMPLEMENTO = SISCOMPLEMENTO || {};


SISCOMPLEMENTO.MaskNOTA = (function () {

    function MaskNOTA() {
        this.inputNOTA = $('.js-nota');
    }

    MaskNOTA.prototype.enable = function () {
        var maskBehavior = function (val) {             
             if (val === '1,0' || val === '1' || val === '10') {
                return '00,0';
            }
             return '0,0';
        };

        var options = {};

        this.inputNOTA.mask(maskBehavior, options);

    }

    return MaskNOTA;

}());


$(function () {
    var maskNOTA = new SISCOMPLEMENTO.MaskNOTA();
    maskNOTA.enable();
})