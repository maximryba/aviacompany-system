    // Функция для включения режима редактирования
    function enableEdit(button) {
        const row = button.closest('tr');
        if (row) {
            const inputs = row.querySelectorAll('input');
            inputs.forEach(input => input.disabled = false);

            row.querySelector('.edit').style.display = 'none'; // Скрываем кнопку "Изменить"
            row.querySelector('.save').style.display = 'inline-block'; // Показываем кнопку "Окей"
        }
    }