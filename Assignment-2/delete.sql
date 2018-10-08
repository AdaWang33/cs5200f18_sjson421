#1
DELETE address
FROM address JOIN person ON address.person_id = person.id
WHERE person.first_name = 'alice' AND address.primary = TRUE;

#2
DELETE widget
FROM widget JOIN page ON widget.page_id = page.id
WHERE page.title = 'Contact' AND
	widget.order = 
	(
		SELECT MAX(`order`)
        FROM (select * FROM widget) AS a
    );

#3
DELETE page
FROM page JOIN website ON page.website_id = website.id
WHERE website.name = 'Wikipedia' AND
	page.updated =
	(
		SELECT MAX(updated)
        FROM (SELECT * FROM page) AS a
    );

#4
#This works because Cascase is set up.
DELETE website
FROM website
WHERE website.name = 'CNET';