SELECT title
FROM books
WHERE CAST(title AS VARCHAR(MAX)) = 'Percy Jackson and the Olympians: The Lightning Thief';




SELECT title
FROM books
WHERE CAST(genre AS VARCHAR(MAX)) = 'fantasy' OR CAST(genre AS VARCHAR(MAX)) = 'dystopia';