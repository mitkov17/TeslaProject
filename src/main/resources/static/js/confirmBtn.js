document.body.addEventListener('click', e => {
    if (e.target.classList.contains('confBtn')) {

        // alerty.confirm(content, opts, onOk, onCancel)
        alerty.confirm(
            'Подтвердить действие?',
            {title: 'Подтверждение', cancelLabel: 'Нет', okLabel: 'Да'},
            function(){
                alerty.toasts('Действие выполнено', {place: 'top'})
            },
            function() {
                alerty.toasts('Действие отменено')
            }
        )
    }
})