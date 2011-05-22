unit constant;

interface

const
  //����ֵ
  RETURN_ZERO = 0;

  //����
  CFG_FILE_NAME = 'config.ini';

  //���ݿ�
  DB_DRIVER_NAME = 'FIREBIRD';
  DB_FILE_NAME = 'system.dll';
  DB_USER_NAME = 'sysdba';
  DB_PASSWORD = 'masterkey';

  //������Ϣ
  ERROR_EMPTY_STR = '������Ϊ��ֵ��';
  ERROR_REG_EXPR = '���������';
  ERROR_TWO_VALUE_GREAT = '��%s����С��%s��';
  ERROR_TWO_VALUE_SMALL = '��%s���ܴ���%s��';

  //�ֶ�ֵ
  BUILDING_NAME = '¥������';
  ADDRESS = '��ϸ��ַ';
  PRICE = '�ۼ�';
  BUILDING_AREA = '�������';
  USABLE_AREA = 'ʹ�����';
  BEDROOM = '��';
  LIVING_ROOM = '��';
  KITCHEN = '��';
  WASHROOM = '��';
  BALCONY = '��̨';
  TOTAL_FLOOR = '��¥��';
  FLOOR = '����¥��';
  GO = '��ת';

  //������ʽ
  REG_TWO_DECIMALS = '^\d{1,5}(.\d{1,2})?$';
  REG_TWO_FIGURES = '^\d{1,2}$';
  REG_THREE_FIGURES = '^\d{1,3}$';
  REG_FIGURES = '^\d$';

  //��ʽ��
  FORMAT_TWO_DECIMALS = '0.00';
  FORMAT_TEN_THOUSAND = 10000;

  //�����ϵӳ��
  PRIMARY_KEY_NAME = 'id';

  COUNT_PER_PAGE = 2;
  FIRST_ROW_NUM = 0;
  FIRST_PAGE_NUM = 1;

  //SQL���ģ��
  SQL_INSERT_RETURN_PK_TEMPLATE = 'INSERT INTO %s (%s) VALUES (%s) RETURNING id;';
  SQL_INSERT_TEMPLATE = 'INSERT INTO %s (%s) VALUES (%s);';
  SQL_UPDATE_TEMPLATE = 'UPDATE %s SET %s WHERE %s;';
  SQL_DELETE_TEMPLATE = 'DELETE FROM %s WHERE %s;';
  SQL_SELECT_BY_ID_TEMPLATE = 'SELECT * FROM %s WHERE id = %s;';

  //SQL���
  SQL_SHH_QUERY = 'SELECT FIRST 2 SKIP %d id "���",'+
  'name "¥������",'+
  'address "��ַ",'+
  'price "�۸���Ԫ/�ף�",'+
  'building_area "���������ƽ���ף�",'+
  'usable_area "ʹ�������ƽ���ף�",'+
  'ROUND(unit_price,2) "���ۣ�Ԫ/ƽ���ף�",'+
  'housing_type "����",'+
  'direction "����",'+
  'floor "����¥�� / ��¥��",'+
  'decoration "װ�޳̶�",'+
  'construct_year "�������"'+
  'FROM vi_second_hand_house ORDER BY id;';
  SQL_SHH_COUNT = 'SELECT COUNT(id) c FROM vi_second_hand_house';

  //��ť����
  BTN_OK = 'ȷ��';

  PAGE_TITLE = '��%d����¼ ��%dҳ ��ǰ��ʾ��%dҳ';

  PAGE_NUM = '��%dҳ';

implementation

end.
