-- 외래 키 제약 조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 디폴트 유저 삽입
INSERT INTO users (user_id, user_uuid, nickname, created_at, profile_img_url, report_received_count) VALUES
                                 (1, UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), 'test_user', NOW(), '프로필 url', 3),
                                 (2, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'test_user2', NOW(), '프로필 url', 4),
                                 (3, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), 'test_user3', NOW(), '프로필 url', 2),
                                 (4, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'test_user4', NOW(), '프로필 url', 4),
                                 (5, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'test_user5', NOW(), '프로필 url', 3)
    ON DUPLICATE KEY UPDATE report_received_count = VALUES(report_received_count);



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
    (7,7, '코',UNHEX(REPLACE('714f9971-a8df-46f8-b0bd-edb13174151b', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/7ea6b1a6-b6c0-4731-87e1-f4844cca01d3/exhibition-photos/9d19ca4a-4372-40fb-9b67-733df0e4a9bd.jpg', NOW()),
    (8,8, '입',UNHEX(REPLACE('02b52d52-b400-4bf1-8879-57f08a69cfd9', '-', '')), 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/6422e443-fedd-4f94-ae26-6e4fa9b58860/exhibition-photos/b342c93a-02fb-4ef0-a12c-f2949501e6fd.jpg', NOW())
    ON DUPLICATE KEY UPDATE photo_id = photo_id;

INSERT INTO admins (admin_id, admin_uuid, email, password, created_at, role) VALUES
 (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'test@admin.com', '$2a$10$hCdKEg9dbBkWoEIqWgG0DuOrxuuQEYHpXmxoA16dJK6WaDsdKpz7K', NOW(),
 'ADMIN')
ON DUPLICATE KEY UPDATE admin_id = admin_id;

-- 추가 신고 데이터
INSERT INTO reports (report_id, reporter_uuid, writer_uuid, description, type, reported_at)
VALUES
    (10, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), '욕설이 포함된 댓글', '부적절한 언어', NOW()),
    (11, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), '광고성 댓글', '스팸', NOW()),
    (12, UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), '타인을 비방하는 내용', '괴롭힘', NOW()),
    (13, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), '반복적인 도배 댓글', '도배성 댓글', NOW()),
    (14, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), '음란한 내용의 댓글', '음란물', NOW()),
    (15, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), '허위 정보 유포', '허위 정보', NOW()),
    (16, UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), '저작권 침해 의심 댓글', '저작권 침해', NOW()),
    (17, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), '불법 행위 조장 댓글', '불법 행위', NOW()),
    (18, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), '정치적 갈등 유발 댓글', '혐오 발언', NOW())
    ON DUPLICATE KEY UPDATE report_id = report_id;

-- 추가 댓글 데이터
INSERT INTO comments (comment_id, comment_uuid, content, report_id)
VALUES
    (2, UNHEX(REPLACE('a1b2c3d4-e5f6-4a5b-9c8d-1e2f3a4b5c6d', '-', '')), '이 멍청한 XX는 뭘 모르네', 10),
    (3, UNHEX(REPLACE('b2c3d4e5-f6a7-5b6c-0d1e-2f3a4b5c6d7e', '-', '')), '여기서 우리 회사 제품 사세요! 특가 할인 중!', 11),
    (4, UNHEX(REPLACE('c3d4e5f6-a7b8-6c7d-1e2f-3a4b5c6d7e8f', '-', '')), '너같은 놈은 살 가치가 없어', 12),
    (5, UNHEX(REPLACE('d4e5f6a7-b8c9-7d8e-2f3a-4b5c6d7e8f9a', '-', '')), '관심 좀 주세요 관심 좀 주세요 관심 좀 주세요', 13),
    (6, UNHEX(REPLACE('e5f6a7b8-c9d0-8e9f-3a4b-5c6d7e8f9a0b', '-', '')), '19금 내용은 비밀 댓글로 보내드립니다', 14),
    (7, UNHEX(REPLACE('f6a7b8c9-d0e1-9f0a-4b5c-6d7e8f9a0b1c', '-', '')), '백신을 맞으면 외계인으로 변합니다', 15),
    (8, UNHEX(REPLACE('a7b8c9d0-e1f2-0a1b-5c6d-7e8f9a0b1c2d', '-', '')), '이 글은 제가 쓴 책에서 그대로 베낀 거예요', 16),
    (9, UNHEX(REPLACE('b8c9d0e1-f2a3-1b2c-6d7e-8f9a0b1c2d3e', '-', '')), '마약 구매하는 방법 알려드립니다', 17),
    (10, UNHEX(REPLACE('c9d0e1f2-a3b4-2c3d-7e8f-9a0b1c2d3e4f', '-', '')), '특정 정당 지지자들은 다 멍청이들이야!', 18)
    ON DUPLICATE KEY UPDATE comment_id = comment_id;

SET FOREIGN_KEY_CHECKS = 1;