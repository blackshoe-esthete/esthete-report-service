-- 외래 키 제약 조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 디폴트 유저 삽입
INSERT INTO users (user_id, user_uuid, nickname, created_at, profile_img_url, report_received_count) VALUES
 (1, UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), 'test_user', NOW(), '프로필 url', 2),
 (2, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'test_user2', NOW(), '프로필 url', 1),
 (3, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), 'test_user3', NOW(), '프로필 url', 1),
 (4, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'test_user4', NOW(), '프로필 url', 2),
 (5, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'test_user5', NOW(), '프로필 url', 1)
ON DUPLICATE KEY UPDATE user_uuid = user_uuid;


INSERT INTO reports (report_id, reporter_uuid, writer_uuid, description, type, reported_at)
VALUES
    (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'Inappropriate content', '광고성 사진', NOW()),
    (2, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'Copyright violation', '명예훼손/저작권 침해', NOW()),
    (3, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'Spam', '도배성 사진', NOW()),
    (4, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), 'Harassment', '도배성 사진', NOW()),
    (5, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'Hate speech', '음란물', NOW()),
    (6, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'Explicit content', '명예훼손/저작권 침해', NOW()),
    (7, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'Violence', '광고성 사진', NOW()),
    (8, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'Misinformation', '도배성 사진', NOW())
    ON DUPLICATE KEY UPDATE report_id = report_id;


INSERT INTO photos (photo_id, report_id, exhibition_title, photo_uuid, photo_img_url, created_at)
VALUES
    (1,1, '태양',UNHEX(REPLACE('e275b6d8-4cc2-4b38-9f89-426f7bf3f1ed', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', NOW()),
    (2,2, '구름',UNHEX(REPLACE('a9db8cfe-f866-4d3d-b377-5ecf689ad91f', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/31216ebf-27a5-4013-bedf-06642401f332/exhibition-photos/98e80855-83ac-4912-8119-373c797924a6.jpg', NOW()),
    (3,3, '바람',UNHEX(REPLACE('12e332f8-0238-445d-9eb8-f1c2f8ac8a77', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/2c8221cf-ea0b-442e-a49e-cd02e4af3ca8/exhibition-photos/38584504-e55f-40de-afeb-7ae754efe347.jpg', NOW()),
    (4,4, '불',UNHEX(REPLACE('047b6575-4025-4229-ab2e-49f4922b6354', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/93dbf19e-6d22-4e3d-8a3b-c04b233bea38/exhibition-photos/b9854f00-2b2f-4ce1-ab5e-3d2c83fd5568.jpg', NOW()),
    (5,5, '꽃',UNHEX(REPLACE('bc19fc1d-ca4d-4da5-92b8-4c0bfc71f4e5', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd/exhibition-photos/86efe632-c2ff-430e-a936-32ad1de6d083.jpg', NOW()),
    (6,6, '말',UNHEX(REPLACE('f0b5158f-230e-4a44-a21f-693a671f100c', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/61110976-0977-4393-8234-1ed9fd510cf3/exhibition-photos/e0a53ff2-5032-4e3e-bc05-42e3013fa8b2.jpg', NOW()),
    (7,7, '코',UNHEX(REPLACE('714f9974-a8df-46f8-b0bd-edb13174151b', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/7ea6b1a6-b6c0-4731-87e1-f4844cca01d3/exhibition-photos/9d19ca4a-4372-40fb-9b67-733df0e4a9bd.jpg', NOW()),
    (8,8, '입',UNHEX(REPLACE('02b52d52-b400-4bf1-8879-57f08a69cfd9', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/6422e443-fedd-4f94-ae26-6e4fa9b58860/exhibition-photos/b342c93a-02fb-4ef0-a12c-f2949501e6fd.jpg', NOW())
    ON DUPLICATE KEY UPDATE photo_id = photo_id;

INSERT INTO admins (admin_id, admin_uuid, email, password, created_at, role) VALUES
 (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'test@admin.com', '$2a$10$hCdKEg9dbBkWoEIqWgG0DuOrxuuQEYHpXmxoA16dJK6WaDsdKpz7K', NOW(),
 'ADMIN')
ON DUPLICATE KEY UPDATE admin_id = admin_id;

INSERT INTO comments (comment_id, comment_uuid, content, report_id)
VALUES
    (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'test_comment', 9)
ON DUPLICATE KEY UPDATE comment_id = comment_id;

INSERT INTO reports (report_id, reporter_uuid, writer_uuid, description, type, reported_at)
VALUES
    (9, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')),
     UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')),
     'test_description', '도배성 댓글', NOW())
ON DUPLICATE KEY UPDATE report_id = report_id;



SET FOREIGN_KEY_CHECKS = 1;