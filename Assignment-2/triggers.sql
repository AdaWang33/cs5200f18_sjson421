DELIMITER $$
DROP TRIGGER IF EXISTS after_website_role_insert$$
CREATE TRIGGER after_website_role_insert
	AFTER INSERT ON website_role
    FOR EACH ROW
BEGIN
	CASE NEW.role
		WHEN 'OWNER' THEN 
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'CREATE',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'DELETE',
				NEW.developer_id, 
				NEW.website_id);
		WHEN 'ADMIN' THEN
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'CREATE',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'DELETE',
				NEW.developer_id, 
				NEW.website_id);
        WHEN 'WRITER' THEN
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'CREATE',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.website_id);
        WHEN 'EDITOR' THEN
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.website_id);
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.website_id);
        ELSE
			INSERT INTO website_priviledge(
				priviledge,
				developer_id,
				website_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.website_id);
	END CASE;
END$$
DELIMITER ;


DELIMITER $$
DROP TRIGGER IF EXISTS after_page_role_insert$$
CREATE TRIGGER after_page_role_insert
	AFTER INSERT ON page_role
    FOR EACH ROW
BEGIN
	CASE NEW.role
		WHEN 'OWNER' THEN 
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'CREATE',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'DELETE',
				NEW.developer_id, 
				NEW.page_id);
		WHEN 'ADMIN' THEN
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'CREATE',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'DELETE',
				NEW.developer_id, 
				NEW.page_id);
        WHEN 'WRITER' THEN
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'CREATE',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.page_id);
        WHEN 'EDITOR' THEN
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.page_id);
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'UPDATE',
				NEW.developer_id, 
				NEW.page_id);
        ELSE
			INSERT INTO page_priviledge(
				priviledge,
				developer_id,
				page_id)
			VALUES (
				'READ',
				NEW.developer_id, 
				NEW.page_id);
	END CASE;
END$$
DELIMITER ;
