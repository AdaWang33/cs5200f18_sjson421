CREATE VIEW `deleveloper_roles_and_privileges` AS
	SELECT 
		d.first_name AS FirstName,
		d.last_name AS LastName,
        d.username AS Username,
        d.email AS Email,
        w.name AS WebsiteName,
        w.visits AS WebsiteVisits,
        w.updated AS WebsiteUpdateDate,
        wr.role AS DevWebRole,
        wp.priviledge As DevWebPrivilege,
        p.title AS PageTitle,
        p.views AS PageViews,
        p.updated AS PageUpdatedDate,
        pr.role AS DevPageRole,
        pp.priviledge AS DevPagePrivilege
	FROM person d, website w, page p, website_role wr, website_priviledge wp, page_role pr, page_priviledge pp
    WHERE 
		d.id = w.developer_id AND
		w.id = p.website_id AND
        wr.developer_id = d.id AND
        wr.website_id = w.id AND
        wp.developer_id = d.id AND
        wp.website_id = w.id AND
        pr.developer_id = d.id AND
        pr.page_id = p.id AND
        pp.developer_id = d.id AND
        pp.page_id = p.id