#1a
SELECT person.*
FROM developer JOIN person ON person.id = developer.id;

#1b
SELECT person.*
FROM developer JOIN person ON person.id = developer.id
WHERE developer.id = 34;

#1c
SELECT person.*
FROM developer 
	JOIN website_role ON developer.id = website_role.developer_id
    JOIN person ON developer.id = person.id
    JOIN website ON website_role.website_id = website.id
WHERE website.name = 'Twitter' AND 
	role <> 'OWNER';

#1d
SELECT person.*
FROM developer JOIN page_role ON developer.id = page_role.developer_id 
	JOIN page ON page.id = page_role.page_id
    JOIN person ON developer.id = person.id
WHERE role = 'REVIEWER' AND page.views < 300000;

#1e
SELECT person.*
FROM page
	JOIN widget ON widget.page_id = page.id
    JOIN website ON page.website_id = website.id
    JOIN page_role ON page.id = page_role.page_id
    JOIN developer ON page_role.developer_id = developer.id
    JOIN person ON developer.id = person.id
WHERE page_role.role = 'WRITER' AND
	website.name = 'CNET' AND
    widget.dtype = 'HEADING';





#2a
SELECT *, MIN(visits)
FROM website;

#2b
SELECT name
FROM website
WHERE id = 678;

#2c
SELECT website.*
FROM website
	JOIN page ON page.website_id = website.id
    JOIN widget ON widget.page_id = page.id
    JOIN page_role ON page_role.page_id = page.id
    JOIN developer ON page_role.developer_id = developer.id
    JOIN person ON person.id = developer.id
WHERE widget.dtype = 'YOUTUBE' AND
	page_role.role = 'REVIEWER' AND
    person.first_name = 'bob';

#2d
SELECT website.*
FROM website
	JOIN website_role ON website_role.website_id = website.id
    JOIN developer ON website_role.developer_id = developer.id
    JOIN person ON developer.id = person.id
WHERE website_role.role = 'OWNER' AND
    person.first_name = 'alice';

#2e
SELECT website.*
FROM website
	JOIN website_role ON website_role.website_id = website.id
    JOIN developer ON website_role.developer_id = developer.id
    JOIN person ON developer.id = person.id
WHERE website_role.role = 'ADMIN' AND
    person.first_name = 'charlie' AND
    website.visits > 6000000;






#3a
SELECT *, MAX(views)
FROM page;

#3b
SELECT title
FROM page
WHERE id = 234;

#3c
SELECT page.*
FROM page
	JOIN page_role ON page_role.page_id = page.id
    JOIN developer on page_role.developer_id = developer.id
    JOIN person ON person.id = developer.id
WHERE person.first_name = 'alice' AND
	page_role.role = 'EDITOR';

#3d
SELECT SUM(page.views)
FROM page
	JOIN website ON page.website_id = website.id
WHERE website.name = 'CNET';

#3e
SELECT AVG(page.views)
FROM page
	JOIN website ON page.website_id = website.id
WHERE website.name = 'Wikipedia';





#4a
SELECT widget.*
FROM widget
	JOIN page ON widget.page_id = page.id
    JOIN website ON page.website_id = website.id
WHERE website.name='CNET';

#4b
SELECT widget.*
FROM widget
	JOIN page ON widget.page_id = page.id
    JOIN website ON page.website_id = website.id
WHERE website.name='CNN' AND
	widget.dtype = 'YOUTUBE';

#4c
SELECT widget.*
FROM widget
	JOIN page ON widget.page_id = page.id
    JOIN website ON page.website_id = website.id
    JOIN page_role ON page_role.page_id = page.id
    JOIN developer ON developer.id = page_role.developer_id
    JOIN person ON developer.id = person.id
WHERE widget.dtype = 'IMAGE' AND
	page_role.role = 'REVIEWER' AND
    person.first_name = 'alice';

#4d
SELECT COUNT(widget.id)
FROM widget
	JOIN page ON widget.page_id = page.id
    JOIN website ON page.website_id = website.id
WHERE website.name = 'Wikipedia';




#5a
SELECT website.name
FROM website
	JOIN website_priviledge ON website_priviledge.website_id = website.id
    JOIN developer ON developer.id = website_priviledge.developer_id
    JOIN person ON developer.id = person.id
WHERE person.first_name = 'bob' AND
	website_priviledge.priviledge = 'DELETE';

#5b
SELECT page.title
FROM page
	JOIN page_priviledge ON page_priviledge.page_id = page.id
    JOIN developer ON developer.id = page_priviledge.developer_id
    JOIN person ON developer.id = person.id
WHERE person.first_name = 'charlie' AND
	page_priviledge.priviledge = 'CREATE';
