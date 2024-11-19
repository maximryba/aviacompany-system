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

function updatePassengerSelects() {
    const passengerCount = document.getElementById('passengerCount').value;
    const passengerSelectsContainer = document.getElementById('passengerSelectsContainer');
    passengerSelectsContainer.innerHTML = '';

    for (let i = 0; i < passengerCount; i++) {
        const select = document.createElement('select');
        select.name = `passengerId${i + 1}`;
        select.className = 'form-control';
        select.innerHTML = `<option value="">-- Выберите пассажира --</option>`;

        const availablePassengers = passengers.filter(passenger => !selectedPassengerIds.includes(passenger.id));

        availablePassengers.forEach(function(passenger) {
            const option = document.createElement('option');
            option.value = passenger.id;
            option.textContent = `${passenger.firstName} ${passenger.lastName}`;
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