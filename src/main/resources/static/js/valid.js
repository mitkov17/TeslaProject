$(document).ready(function(){
    $('#consultation-form').validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 30
            },
            surname: {
                required: true,
                minlength: 2,
                maxlength: 30
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 12
            },
            email: {
                required: true,
                email: true
            },
            nameCar: {
                required: true,
                minlength: 2,
                maxlength: 30
            }
        },
        messages: {
            name: {
                required: "Please, enter your name",
                minlength: jQuery.validator.format("No less than {0} symbols!"),
                maxlength: jQuery.validator.format("No less than {0} symbols!")
            },
            surname: {
                required: "Please, enter your surname",
                minlength: jQuery.validator.format("No less than {0} symbols!"),
                maxlength: jQuery.validator.format("No less than {0} symbols!")
            },
            phone: {
                required : "Please, enter your phone number",
                minlength: jQuery.validator.format("No less than {0} symbols!"),
                maxlength: jQuery.validator.format("No less than {0} symbols!")
            },
            email: {
              required: "Please, enter your email",
              email: "The email entered must be in the format name@domain.com"
            },
            nameCar: {
                required: "Please, enter model's name",
                minlength: jQuery.validator.format("No less than {0} symbols!"),
                maxlength: jQuery.validator.format("No less than {0} symbols!")
            },
        }
    });

    document.querySelector('#idName').addEventListener('keyup', function(){
        this.value = this.value.replace(/^[.:;№"!#$%&@'*+/=?^_`(){|}~-]/g, '');
    });

    document.querySelector('#idSurname').addEventListener('keyup', function(){
        this.value = this.value.replace(/^[.:;№"!#$%&@'*+/=?^_`(){|}~-]/g, '');
    });

    document.querySelector('#modelCar').addEventListener('keyup', function(){
        this.value = this.value.replace(/^[.:;№"!#$%&@'*+/=?^_`(){|}~-]/g, '');
    });
});