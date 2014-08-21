INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (1,'PERSONAS', 1, 1, 'persona-icon', 'default', 1);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (2,'NETWORK', 1, 1, '', 'hidden', 2);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (3,'ROAMING CATEGORY', 1, 1, '', 'hidden', 3);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (4,'DOMESTIC ARPU', 1, 1, '', 'hidden', 4);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (5,'PAYMENT TYPE', 1, 1, '', 'hidden', 5);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (6,'DEVICE TYPE', 1, 1, '', 'hidden', 6);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (7,'TRAFFIC PATTERN', 1, 1, '', 'hidden', 7);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (8,'TRAVEL DURATION', 1, 1, '', 'hidden', 8);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (9,'OTHER COUNTRIES TRAVELLED', 1, 1, '', 'hidden', 9);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (10,'DOMESTIC LOCATION', 1, 1, '', 'hidden', 10);
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (11,'DOMESTIC DATA PROFILE', 1, 1, '', 'hidden', 11);	
    
INSERT INTO attribute(id, attribute_name, module_id, type, icon, view_type, display_order)
    VALUES (12,'ROAMING DATA PROFILE', 1, 1, '', 'hidden', 12);	
    

INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('BUSINESS', 1, 'business-icon', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LEISURE', 1, 'lesiure-icon', 2);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FLASHPACKER', 1, 'flashpacker-icon', 3);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FAMILY', 1, 'family-icon', 4);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LABOUR', 1, 'labour-icon', 5);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('OTHER', 1, 'otherpersona-icon', 6);
    
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SAME NETWORK GROUP', 2, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('DIFFERENT NETWORK GROUP', 2, '', 2);

    
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SILENT', 3, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('VALUE', 3, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PREMIUM', 3, '', 3);
    
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LOW', 4, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('MEDIUM', 4, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('HIGH', 4, '', 3);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PRE PAID', 5, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('POST PAID', 5, '', 2);
    
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('VALUE PHONE', 6, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FEATURE PHONE', 6, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SMART PHONE', 6, '', 3);
 INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PREMIUM PHONE', 6, '', 4);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TABLET', 6, '', 5);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('DONGLE', 6, '', 6);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('M2M', 6, '', 7);
    
    -- TRAFFIC PATTERN
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('MT ARBITRAGE', 7, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TRAVEL SIM', 7, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('DUAL SIM', 7, '', 3);
 INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('VOIP', 7, '', 4);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SEAMLESS CALL BACK', 7, '', 5);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('AUDIO CONFERENCE', 7, '', 6);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('INTERNATIONAL BUSINESS', 7, '', 7);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('M2M', 7, '', 8);
    
    --TRAVEL DURATION
    
        INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('WEEKDAY ONLY', 8, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('WEEKEND ONLY', 8, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('WEEKDAY PLUS WEEKEND', 8, '', 3);
 INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TWO WEEKS PLUS', 8, '', 4);
    
    -- OTHER COUNTRIES TRAVELLED 
            INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('NEIGHBORING', 9, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LEISURE', 9, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LEISURE PREMIUM', 9, '', 3);
 INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('BUSINESS', 9, '', 4);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LOW GDP', 9, '', 5);
    
    -- DOMESTIC LOCATION
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('HOME', 10, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('WORK', 10, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LEISURE', 10, '', 3);
    
    
        -- DOMESTIC DATA PROFILE
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('ARTS & ENTERTAINMENT', 11, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('AUTO & VEHICLES', 11, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('BEAUTY & FITNESS', 11, '', 3);
 INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('BUSINESS', 11, '', 4);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TECHNOLOGY & COMPUTING', 11, '', 5);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PERSONAL FINANCE', 11, '', 6);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FOOD & DRINK', 11, '', 7);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('HOBBIES & INTEREST', 11, '', 8);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FAMILY & PARENTING', 11, '', 9);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('HOME & GARDEN', 11, '', 10);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LAW & GOVERNMENT', 11, '', 11);
    
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('NEWS', 11, '', 12);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('ONLINE COMMUNITY', 11, '', 13);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SOCIETY', 11, '', 14);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PETS', 11, '', 15);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SCIENCE', 11, '', 16);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SPORTS', 11, '', 17);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SHOPPING', 11, '', 18);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('STYLE & FASHION', 11, '', 19);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TRAVEL', 11, '', 20);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('JOBS & EDUCATION', 11, '', 21);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('RELIGION & SPIRITUALITY', 11, '', 22);
    
    
    
    
         -- ROAMING DATA PROFILE
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('ARTS & ENTERTAINMENT', 12, '', 1);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('AUTO & VEHICLES', 12, '', 2);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('BEAUTY & FITNESS', 12, '', 3);
 INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('BUSINESS', 12, '', 4);
INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TECHNOLOGY & COMPUTING', 12, '', 5);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PERSONAL FINANCE', 12, '', 6);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FOOD & DRINK', 12, '', 7);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('HOBBIES & INTEREST', 12, '', 8);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('FAMILY & PARENTING', 12, '', 9);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('HOME & GARDEN', 12, '', 10);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('LAW & GOVERNMENT', 12, '', 11);
    
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('NEWS', 12, '', 12);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('ONLINE COMMUNITY', 12, '', 13);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SOCIETY', 12, '', 14);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('PETS', 12, '', 15);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SCIENCE', 12, '', 16);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SPORTS', 12, '', 17);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('SHOPPING', 12, '', 18);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('STYLE & FASHION', 12, '', 19);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('TRAVEL', 12, '', 20);
    
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('JOBS & EDUCATION', 12, '', 21);
    INSERT INTO attribute_category(category_name, attribute_id, icon, display_order)
    VALUES ('RELIGION & SPIRITUALITY', 12, '', 22);
   