document.body.addEventListener('click', e => {
    if (e.target.classList.contains('confBtn')) {

        // alerty.confirm(content, opts, onOk, onCancel)
        alerty.confirm(
            'Вы уверены, что хотите заказать этот электромобиль?',
            {title: 'Подтверждение заказа', cancelLabel: 'Отменить', okLabel: 'Заказать'},
            function(){
                alerty.toasts('Заказ подтверждён', {place: 'top'})
            },
            function() {
                alerty.toasts('Заказ отменён')
            }
        )
    }
})