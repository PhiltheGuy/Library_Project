//This search feature returns all available books with a given title.
//That is to say, if the book has check_out_by set as null, then
//it can be returned


import React, { useState } from 'react';
import { fetchBooksWithFilters } from '../api/api';

const BookQuery = () => {
    const [filters, setFilters] = useState({ minPage: '', maxPage: '', author: '', genre: '' });
    const [results, setResults] = useState([]);
    const [error, setError] = useState(null);

    const handleQuery = async (event) => {
        event.preventDefault();
        try {
            const data = await fetchBooksWithFilters(filters);
            setResults(data);
            setError(null);
        } catch (err) {
            setError('Error fetching books');
        }
    };

    const handleChange = (e) => {
        setFilters({ ...filters, [e.target.name]: e.target.value });
    };

    return (
        <div>
            <h2>Advanced Book Query</h2>
            <form onSubmit={handleQuery}>
                <input type="number" name="minPage" placeholder="Min Pages" value={filters.minPage} onChange={handleChange} />
                <input type="number" name="maxPage" placeholder="Max Pages" value={filters.maxPage} onChange={handleChange} />
                <input type="text" name="author" placeholder="Author" value={filters.author} onChange={handleChange} />
                <input type="text" name="genre" placeholder="Genre" value={filters.genre} onChange={handleChange} />
                <button type="submit">Search</button>
            </form>
            {error && <p>{error}</p>}
            <ul>
                {results.map((book) => (
                    <li key={book.id}>{book.title}</li>
                ))}
            </ul>
        </div>
    );
};

export default BookQuery;
