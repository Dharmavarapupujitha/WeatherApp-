const apiKey = "99212b72d971861a8426645a44b5f396";

async function getWeather() {
  const city = document.getElementById('cityInput').value.trim();
  const weatherDiv = document.getElementById('weatherResult');

  if (city === "") {
    weatherDiv.innerHTML = "Please enter a city name.";
    return;
  }

  weatherDiv.innerHTML = "Loading...";

  try {
    const response = await fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`
    );

    if (!response.ok) {
      weatherDiv.innerHTML = "City not found. Please check the city name.";
      return;
    }

    const data = await response.json();

    weatherDiv.innerHTML = `
      <h3>Weather in ${data.name}, ${data.sys.country}</h3>
      <p>Temperature: ${data.main.temp}°C</p>
      <p>Weather: ${data.weather[0].description}</p>
      <p>Humidity: ${data.main.humidity}%</p>
      <p>Wind Speed: ${data.wind.speed} m/s</p>
    `;
  } catch (error) {
    weatherDiv.innerHTML = "Error fetching weather data.";
    console.error(error);
  }
}
