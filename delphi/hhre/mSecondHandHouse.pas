unit mSecondHandHouse;

interface

uses
  orm;

type
  [TTableInfo('TB_SECOND_HAND_HOUSE', '���ַ�')]
  TSecondHandHouse = class(TModel)
  strict private
    AId: Integer;
    APrice: Double;
    ABuildingArea: Double;
    AUsableArea: Double;
    APropertyId: Integer;
    APropTypeId: Integer;
    APropStructId: Integer;
    AConstructTypeId: Integer;
    AVisitTimeId: Integer;
  public
    [TFieldInfo('ID', '���')]
    property Id: Integer read AId write AId;
    [TFieldInfo('PRICE', '�ۼ�')]
    property Price: Double read APrice write APrice;
    [TFieldInfo('BUILDING_AREA', '�������')]
    property BuildingArea: Double read ABuildingArea write ABuildingArea;
    [TFieldInfo('USABLE_AREA', 'ʹ�����')]
    property UsableArea: Double read AUsableArea write AUsableArea;
    [TFieldInfo('PROPERTY_ID', '��Ȩ����')]
    property PropertyId: Integer read APropertyId write APropertyId;
    [TFieldInfo('PROP_TYPE_ID', 'סլ���')]
    property PropTypeId: Integer read APropTypeId write APropTypeId;
    [TFieldInfo('PROP_STRUCT_ID', '���ݽṹ')]
    property PropStructId: Integer read APropStructId write APropStructId;
    [TFieldInfo('CONSTRUCT_TYPE_ID', '�������')]
    property ConstructTypeId: Integer read AConstructTypeId write AConstructTypeId;
    [TFieldInfo('VISIT_TIME_ID', '����ʱ��')]
    property VisitTimeId: Integer read AVisitTimeId write AVisitTimeId;
  end;

implementation

end.
