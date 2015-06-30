/*
���������н����ϵͳ ���ݿ�
*/

/* �û��� */
CREATE TABLE tb_user (
  id INTEGER NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

/* �û����� */
CREATE SEQUENCE seq_user;
ALTER SEQUENCE seq_user RESTART WITH 1;

SET TERM ^ ;

CREATE TRIGGER trig_user FOR tb_user
ACTIVE BEFORE INSERT POSITION 0
AS
BEGIN
  IF (NEW.id IS NULL) THEN 
	NEW.id = GEN_ID(seq_user, 1);
END^

SET TERM ; ^

/* ��ұ� */
CREATE TABLE tb_buyer
(
id INTEGER NOT NULL,
name VARCHAR(50) NOT NULL,
sex CHAR DEFAULT '1', /* 1���У�0��Ů */
age SMALLINT,
province_id SMALLINT,
credential_id SMALLINT,
credential_number VARCHAR(50),
phone1 VARCHAR(20),
phone2 VARCHAR(20),
phone3 VARCHAR(20),
mobile1 VARCHAR(20),
mobile2 VARCHAR(20),
mobile3 VARCHAR(20),
email1 VARCHAR(50),
email2 VARCHAR(50),
email3 VARCHAR(50),
address1 VARCHAR(500),
address2 VARCHAR(500),
address3 VARCHAR(500),

price NUMERIC,
max_price NUMERIC,
min_price NUMERIC,
bedroom_count SMALLINT,
drawing_room_count SMALLINT,
kitchen_count SMALLINT,
washroom_count SMALLINT,
area NUMERIC,
max_area NUMERIC,
min_area NUMERIC,
structure_id SMALLINT,
floor_request_id SMALLINT,
�Ƿ�Ҫ���г���
�Ƿ�Ҫ������
�Ƿ�Ҫ���г�λ
�Ƿ�Ҫ����λ
�Ƿ���ѧ����
�����Ƿ��д���
�����Ƿ��е���
�����Ƿ��й�����ͨ

PRIMARY KEY (id)
)

/* ����� */
CREATE TABLE cfg_area
(
id SMALLINT NOT NULL,
name VARCHAR(50) NOT NULL,
type CHAR DEFAULT '0', /* 0��ʡ����������ֱϽ�У�1�����У�2�������ء��� */
parent_id SMALLINT,
enable CHAR DEFAULT '1', /* 1�����ã�0�������� */
PRIMARY KEY (id)
)

INSERT INTO cfg_area (id, name) VALUES (111, '�Ϻ�');
INSERT INTO cfg_area (id, name) VALUES (112, 'ɽ��');
INSERT INTO cfg_area (id, name) VALUES (113, '�㶫');
INSERT INTO cfg_area (id, name) VALUES (114, '����');
INSERT INTO cfg_area (id, name) VALUES (115, '���ɹ�');
INSERT INTO cfg_area (id, name) VALUES (116, '����');
INSERT INTO cfg_area (id, name) VALUES (117, '�ຣ');
INSERT INTO cfg_area (id, name) VALUES (118, '�Ĵ�');
INSERT INTO cfg_area (id, name) VALUES (119, '������');
INSERT INTO cfg_area (id, name) VALUES (120, '����');
INSERT INTO cfg_area (id, name) VALUES (121, '����');
INSERT INTO cfg_area (id, name) VALUES (122, '����');
INSERT INTO cfg_area (id, name) VALUES (123, '����');
INSERT INTO cfg_area (id, name) VALUES (124, '���');
INSERT INTO cfg_area (id, name) VALUES (125, 'ɽ��');
INSERT INTO cfg_area (id, name) VALUES (126, '�ӱ�');
INSERT INTO cfg_area (id, name) VALUES (127, '�½�');
INSERT INTO cfg_area (id, name) VALUES (128, '����');
INSERT INTO cfg_area (id, name) VALUES (129, '����');
INSERT INTO cfg_area (id, name) VALUES (130, '����');
INSERT INTO cfg_area (id, name) VALUES (131, '����');
INSERT INTO cfg_area (id, name) VALUES (132, '����');
INSERT INTO cfg_area (id, name) VALUES (133, '����');
INSERT INTO cfg_area (id, name) VALUES (134, '�㽭');
INSERT INTO cfg_area (id, name) VALUES (135, '����');
INSERT INTO cfg_area (id, name) VALUES (136, '����');
INSERT INTO cfg_area (id, name) VALUES (137, '����');
INSERT INTO cfg_area (id, name) VALUES (138, '����');
INSERT INTO cfg_area (id, name) VALUES (139, '����');
INSERT INTO cfg_area (id, name) VALUES (140, '����');
INSERT INTO cfg_area (id, name) VALUES (141, '���');
INSERT INTO cfg_area (id, name) VALUES (142, '̨��');
INSERT INTO cfg_area (id, name) VALUES (143, '����');


INSERT INTO cfg_area (id, name, type, parent_id) VALUES (251, '�Ͼ�', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250002, '����', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250003, '����', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250004, '��ͨ', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250005, '����', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250006, '��', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250007, '����', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250008, '����', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250009, '���Ƹ�', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250010, '̩��', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250011, '����', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250012, '�γ�', '2', 16);
INSERT INTO cfg_area (id, name, type, parent_id) VALUES (250013, '��Ǩ', '2', 16);

CREATE TABLE CFG_DATA (
  ID      SMALLINT NOT NULL,
  NAME    VARCHAR(50) NOT NULL,
  "TYPE"  VARCHAR(3) NOT NULL, /* 1:��Ȩ����;2:����;3:��ҵ����;4:סլ���;5:�������;6:���ݽṹ;7:װ�޳̶�;8:����ʱ��;9:������ʩ;10:��Դ��ɫ;11:������� */
  ENABLE  CHAR DEFAULT '1',
  /* Keys */
  PRIMARY KEY (ID)
);

/* ֤���� */
CREATE TABLE cfg_credential
(
id SMALLINT NOT NULL,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
)

INSERT INTO cfg_credential (id, name) VALUES (1, 'ʡ��֤');

/* ¥��Ҫ��� */
CREATE TABLE cfg_floor_request
(
id SMALLINT NOT NULL,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
)

INSERT INTO cfg_floor_request (id, name) VALUES (1, '����¥�㶼����');
INSERT INTO cfg_floor_request (id, name) VALUES (2, '��Ҫ��¥');
INSERT INTO cfg_floor_request (id, name) VALUES (3, '��Ҫ��¥');
INSERT INTO cfg_floor_request (id, name) VALUES (4, '��Ҫ��¥�Ͷ�¥');

/* ���ݽṹ�� */
CREATE TABLE cfg_structure
(
id SMALLINT NOT NULL,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
)

INSERT INTO cfg_structure (id, name) VALUES (1, '���ҳ���');

/* ���ַ��� */
CREATE TABLE tb_shh
(
id INTEGER NOT NULL,
name VARCHAR(50) NOT NULL,
province_id SMALLINT NOT NULL,
city_id SMALLINT NOT NULL,
borough_id SMALLINT NOT NULL,
address VARCHAR(100) NOT NULL,
price NUMERIC NOT NULL,
building_area NUMERIC NOT NULL,
usable_area NUMERIC NOT NULL,
bedroom_count SMALLINT NOT NULL,
drawing_room_count SMALLINT NOT NULL,
kitchen_count SMALLINT NOT NULL,
washroom_count SMALLINT NOT NULL,
balcony_count SMALLINT NOT NULL,
orientation_id SMALLINT NOT NULL,
total_floor SMALLINT NOT NULL,
floor SMALLINT NOT NULL,
property_id SMALLINT NOT NULL,
PRIMARY KEY (id)
)

CREATE SEQUENCE gen_shh;
ALTER SEQUENCE gen_shh RESTART WITH 1;

SET TERM ^ ;

CREATE TRIGGER trig_shh FOR tb_shh
ACTIVE BEFORE INSERT POSITION 0
AS
BEGIN
  IF (NEW.id IS NULL) THEN 
	NEW.id = GEN_ID(gen_shh, 1);
END^

SET TERM ; ^

