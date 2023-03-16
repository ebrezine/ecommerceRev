INSERT INTO product (id, quantity, price, description, image, name, country) VALUES (
    1,
    10,
    20.00,
    'A nice pair of headphones',
    'https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp',
    'Headphones',
    1
),
(
    2,
    5,
    45.00,
    'A nice TeeShirt',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png',
    'TeeShirt',
    2
),
(
    3,
    20,
    2.50,
    'A reusable shopping bag',
    'https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png',
    'Shopping Bag',
    1
),
(
    4,
    20,
    10.00,
    'A fancy cap for a fancy person',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png',
    'Baseball Cap',
    2
),
(
    5,
    3,
    80.00,
    'A nice coat',
    'https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png',
    'Coat',
    3
);

INSERT INTO users (id, email, password, first_name, last_name, answer, question) VALUES (
    1,
    'testuser@gmail.com',
    'password',
    'Testing',
    'User',
    'testcity',
    'In what city were you born?'
);


INSERT INTO product_details (id, brand_name, model_name, product_color, product_manufacturer, product_weight) VALUES (
    1,
    'Sony',
    'H200-BLK',
    'Black',
    'Anker',
    '9.3 oz'
),
(
    2,
    'GreatTees',
    'M-L',
    'Cream',
    'GreatTees Inc.',
    '4.8 oz'
),
(
    3,
    'Alliance Bags',
    'CW-10',
    'Off white with green',
    'Lots of Deals LLC',
    '1.5 oz'
),
(
    4,
    'HatDog',
    'G-100',
    'Gray',
    'HatDog Caps',
    '4.0 oz'
),
(
    5,
    'Elesol',
    'AT-Med',
    'Tan',
    'R&M Inc.',
    '15.4 oz'
);