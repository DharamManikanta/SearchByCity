import React, { useState, useEffect } from "react";
import "../styles/theme.css";

const SearchBar = () => {
  const [query, setQuery] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const [selectedCity, setSelectedCity] = useState(null);
  const [cityFound, setCityFound] = useState(true);

  useEffect(() => {
    if (query.trim() === "") {
      setSuggestions([]);
      return;
    }

    const fetchCities = async () => {
      try {
        const res = await fetch(`http://localhost:8080/cities/suggest?keyword=${query}`);
        const data = await res.json();
        setSuggestions(data);
        setCityFound(data.length > 0);
      } catch (err) {
        console.error("Error fetching cities:", err);
        setCityFound(false);
      }
    };

    const delay = setTimeout(fetchCities, 300);
    return () => clearTimeout(delay);
  }, [query]);

  const handleSelect = (city) => {
    setSelectedCity(city.cityName);
    setQuery(city.cityName);
    setSuggestions([]);
    setCityFound(true);
  };

  return (
    <div className="container">
      <h2>🔍 Search City</h2>

      <input
        type="text"
        placeholder="Start typing a city name..."
        value={query}
        onChange={(e) => {
          setQuery(e.target.value);
          setSelectedCity(null);
        }}
      />

      {suggestions.length > 0 && (
        <ul className="suggestions">
          {suggestions.map((city) => (
            <li key={city.cityId} onClick={() => handleSelect(city)}>
              {city.cityName}
            </li>
          ))}
        </ul>
      )}

      {query && !cityFound && (
        <div className="result-message error">
          No cities found matching "{query}"
        </div>
      )}

      {selectedCity && cityFound && (
        <div className="result-message success">
          <strong>{selectedCity}</strong> was found!
        </div>
      )}
    </div>
  );
};

export default SearchBar;
