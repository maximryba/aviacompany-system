function fetchCities(field) {
        const query = document.getElementById(field).value;
        const suggestionsList = document.getElementById(field + 'Suggestions');

        if (query.length < 2) {
            suggestionsList.innerHTML = '';
            return;
        }

        fetch('/search/cities?field=' + field + '&query=' + query)
            .then(response => response.json())
            .then(cities => {
				console.log(cities);
				console.log(query);
                suggestionsList.innerHTML = '';
                cities.forEach(city => {
                    const li = document.createElement('li');
                    li.textContent = city;
                    li.onclick = () => selectCity(field, city);
                    suggestionsList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching cities:', error));
    }

    function selectCity(field, cityName) {
        document.getElementById(field).value = cityName;
        document.getElementById(field + 'Suggestions').innerHTML = '';
    }