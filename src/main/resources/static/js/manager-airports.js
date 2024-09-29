// Функция для включения режима редактирования
function enableEdit(button) {
    const row = button.closest('tr');

    if (row) {
        // Разблокируем все поля ввода и чекбокс в данной строке
        const inputs = row.querySelectorAll('input');
        inputs.forEach(input => input.disabled = false);

        // Скрыть кнопку "Изменить" и показать кнопку "Окей"
        row.querySelector('.edit').style.display = 'none';
        row.querySelector('.save').style.display = 'inline-block';
    } else {
        console.error('Row not found');
    }
}

document.querySelectorAll('.delete').forEach(function(button) {
    button.addEventListener('click', function (event) {
        if (!confirm('Вы уверены, что хотите удалить этот самолет?')) {
            event.preventDefault();
        }
    });
});