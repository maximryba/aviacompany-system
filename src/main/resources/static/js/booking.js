let passengers = [];
let selectedPassengerIds = [];

function fetchPassengers(userId) {
    fetch(`/api/passengers/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            passengers = data;
            console.log('Passengers loaded:', passengers);
            updatePassengerSelects();
        })
        .catch(error => console.error('Error fetching passengers:', error));
}

let selectedSeats = [];

function selectSeat(seatElement) {
    const seatId = seatElement.getAttribute("data-seat-id");
    
    if (seatElement.classList.contains("available") && selectedSeats.length < document.getElementById("passengerCount").value) {
        if (selectedSeats.includes(seatId)) {
            seatElement.classList.remove("selected");
            selectedSeats = selectedSeats.filter(id => id !== seatId);
        } else {
            seatElement.classList.add("selected");
            selectedSeats.push(seatId);
        }
    } else if (seatElement.classList.contains("occupied")) {
        alert("Это место занято.");
    } else {
        alert("Вы можете выбрать только количество мест, равное количеству пассажиров.");
    }

    console.log("Выбранные места:", selectedSeats);
}

function toggleProfileMenu() {
    const menu = document.getElementById('profileMenu');
    menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
}

// Закрытие меню при клике вне его
window.onclick = function(event) {
    if (!event.target.matches('.profile-container *')) {
        const menu = document.getElementById('profileMenu');
        if (menu.style.display === 'block') {
            menu.style.display = 'none';
        }
    }
}

function updatePassengerSelects() {
    const passengerCount = document.getElementById('passengerCount').value;
    const passengerSelectsContainer = document.getElementById('passengerSelectsContainer');
    passengerSelectsContainer.innerHTML = '';

    for (let i = 0; i < passengerCount; i++) {
        const select = document.createElement('select');
        select.name = passengerId${i + 1};
        select.className = 'form-control';
        select.innerHTML = <option value="">-- Выберите пассажира --</option>;

        const availablePassengers = passengers.filter(passenger => !selectedPassengerIds.includes(passenger.id));

        availablePassengers.forEach(function(passenger) {
            const option = document.createElement('option');
            option.value = passenger.id;
            option.textContent = ${passenger.firstName} ${passenger.lastName};
            select.appendChild(option);
        });

        if (selectedPassengerIds[i]) {
            select.value = selectedPassengerIds[i];
        }

        select.addEventListener('change', function() {
            const selectedId = this.value;

            selectedPassengerIds[i] = selectedId ? selectedId : null;

            updateAvailableOptions();
        });

        passengerSelectsContainer.appendChild(select);
    }
}


function updateAvailableOptions() {
    const selects = document.querySelectorAll('select[name^="passengerId"]');

    const selectedIds = Array.from(selects).map(select => select.value).filter(id => id);

    selects.forEach((select, index) => {
        const availablePassengers = passengers.filter(passenger => !selectedIds.includes(passenger.id));

        select.innerHTML = `<option value="">-- Выберите пассажира --</option>`;
        availablePassengers.forEach(function(passenger) {
            const option = document.createElement('option');
            option.value = passenger.id;
            option.textContent = `${passenger.firstName} ${passenger.lastName}`;
            select.appendChild(option);
        });

        if (selectedPassengerIds[index]) {
            select.value = selectedPassengerIds[index];
        }
    });
}

const userId = window.location.pathname.split('/').pop();
fetchPassengers(userId);

function selectSeat(seat) {
    if (seat.classList.contains('available')) {
        seat.classList.toggle('selected');
    }
}