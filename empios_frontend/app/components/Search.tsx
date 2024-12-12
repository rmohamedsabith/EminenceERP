import React, { useState, ChangeEvent, KeyboardEvent } from "react";

interface SearchProps {
  options: string[]; // List of search options
  placeholder?: string; // Placeholder for the input field
  onSearch: (value: string) => void; // Callback when a search is performed
}

const Search: React.FC<SearchProps> = ({
  options,
  placeholder = "Search...",
  onSearch,
}) => {
  const [query, setQuery] = useState<string>(""); // Current input value
  const [filteredOptions, setFilteredOptions] = useState<string[]>([]); // Filtered list
  const [isDropdownOpen, setDropdownOpen] = useState<boolean>(false); // Dropdown visibility

  // Handle input change and filter options
  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setQuery(value);

    if (value.trim()) {
      const matches = options.filter((option) =>
        option.toLowerCase().includes(value.toLowerCase())
      );
      setFilteredOptions(matches);
      setDropdownOpen(true);
    } else {
      setFilteredOptions([]);
      setDropdownOpen(false);
    }
  };

  // Handle option click
  const handleOptionClick = (option: string) => {
    setQuery(option); // Set input value to the clicked option
    setDropdownOpen(false);
    onSearch(option); // Trigger the search callback
  };

  // Handle keyboard events
  const handleKeyDown = (e: KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter" && query.trim()) {
      setDropdownOpen(false);
      onSearch(query); // Trigger search when Enter is pressed
    }
  };

  return (
    <div className="relative w-full flex flex-row-reverse justify-between items-center mb-3 mt-1 pl-3">
      <div className="ml-3">
        <div className="w-full max-w-sm min-w-[300px] relative">
          <div className="relative">
            <input
              className="bg-white w-full pr-11 h-10 pl-3 py-2 placeholder:text-slate-400 text-slate-700 text-sm border border-slate-200 rounded transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-400 shadow-sm focus:shadow-md"
              placeholder={placeholder}
              value={query}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
            />
            <button
              className="absolute h-8 w-8 right-1 top-1 my-auto px-2 flex items-center bg-white rounded"
              type="button"
              onClick={() => onSearch(query)}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="3"
                stroke="currentColor"
                className="w-8 h-8 text-slate-600"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"
                />
              </svg>
            </button>
          </div>
          {isDropdownOpen && filteredOptions.length > 0 && (
            <ul className="absolute z-10 bg-white border border-slate-200 rounded shadow-md mt-2 max-h-40 overflow-auto w-full">
              {filteredOptions.map((option, index) => (
                <li
                  key={index}
                  className="px-4 py-2 cursor-pointer hover:bg-slate-100 text-slate-700"
                  onClick={() => handleOptionClick(option)}
                >
                  {option}
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  );
};

export default Search;
