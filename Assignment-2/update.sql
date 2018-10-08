#1
SET SQL_SAFE_UPDATES = 0;
UPDATE phone
	JOIN person ON phone.person_id = person.id
SET phone = '333-444-5555'
WHERE person.first_name = 'charlie' AND
    phone.primary = TRUE;















#2
UPDATE widget
SET `order` = 1
WHERE `order` = 2 AND 
	page_id IN
    (
		SELECT page_id
		FROM (SELECT * FROM widget) AS w
		WHERE name = 'head345'
    );
UPDATE widget
SET `order` = 2
WHERE `order` = 3 AND 
	page_id IN
    (
		SELECT page_id
		FROM (SELECT * FROM widget) AS w
		WHERE name = 'head345'
    );
UPDATE widget
SET `order` = 3
WHERE name = 'head345';













#3
UPDATE page JOIN website ON page.website_id = website.id
SET page.title = CONCAT('CNET - ', page.title)
WHERE website.name = 'CNET';














#4
UPDATE page_role 
	JOIN developer ON page_role.developer_id = developer.id
    JOIN person ON person.id = developer.id
    JOIN page ON page_role.page_id = page.id
SET page_role.role = 'WRITER'
WHERE person.first_name = 'bob' AND
	page.title = 'CNET - Home';

UPDATE page_role 
	JOIN developer ON page_role.developer_id = developer.id
    JOIN person ON person.id = developer.id
    JOIN page ON page_role.page_id = page.id
SET page_role.role = 'REVIEWER'
WHERE person.first_name = 'charlie' AND
	page.title = 'CNET - Home';