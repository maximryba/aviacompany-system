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


// Обработка кнопки "Удалить"
document.querySelectorAll('.delete').forEach(function(button) {
    button.addEventListener('click', function (event) {
        if (confirm('Вы уверены, что хотите удалить этот самолет?')) {
            const row = button.closest('tr');
            const planeId = row.querySelector('td:first-child').innerText; // Получаем ID самолета

            // Отправляем запрос на сервер для удаления через JSON
            fetch(`/manager/airplanes/delete/${planeId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                if (response.ok) {
                    alert('Самолет успешно удален!');
                    window.location.reload(); // Перезагрузить страницу для обновления списка
                } else {
                    alert('Ошибка при удалении самолета.');
                }
            }).catch(error => {
                console.error('Ошибка:', error);
            });
        } else {
            event.preventDefault();
        }
    });
});

document.querySelector('.add').addEventListener('click', function(event) {
    event.preventDefault();

    fetch('/manager/airplanes/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: document.querySelector('input[name="name"]').value,
            fuelCapacity: document.querySelector('input[name="fuelСapacity"]').value,
            passengerCapacity: document.querySelector('input[name="passengerСapacity"]').value,
            serviceCost: document.querySelector('input[name="serviceСost"]').value
        })
    }).then(response => {
        if (response.ok) {
            window.location.href = '/manager/airplanes/all';
        } else {
            alert('Ошибка при добавлении самолёта');
        }
    });
});