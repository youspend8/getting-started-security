INSERT INTO member (member_id, name, password, role, created_at, modified_at) VALUES (1, '관리자', '$2a$10$0uTd.5.WLqSxMSeVeSMqY.Tkn7ouqM/ljGqvmR3kWc3sOaNA1hpkO', 'ADMINISTRATOR', NOW(), NOW());

INSERT INTO post (post_id, content, remove, member_id, created_at, modified_at) VALUES (1, '게시글 내용', false, 1, NOW(), NOW())
