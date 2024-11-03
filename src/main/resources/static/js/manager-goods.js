  // Функция для включения режима редактирования
    function enableEdit(button) {
        const row = button.closest('tr');

        if (row) {
            const inputs = row.querySelectorAll('input');
            const selects = row.querySelectorAll('select');
            inputs.forEach(input => input.disabled = false);
            selects.forEach(select => select.disabled = false);

            row.querySelector('.edit').style.display = 'none'; // Скрываем кнопку "Изменить"
            row.querySelector('.save').style.display = 'inline-block'; // Показываем кнопку "Окей"
        }
    }

    // Обработка кнопки "Удалить"
    document.querySelectorAll('.delete').forEach(function(button) {
        button.addEventListener('click', function(event) {
            if (confirm('Вы уверены, что хотите удалить этот товар?')) {
                // Обработка удаления товара
            } else {
                event.preventDefault();
            }
        });
    });