unit mHouse;

interface

uses
  orm;

type
  [TTableInfo('TB_HOUSE', '����')]
  THouse = class(TModel)
  strict private
    AId: Integer;
    AName: string;
    AProvinceId: Integer;
    ACityId: Integer;
    ADistrictId: Integer;
    AAddress: string;
    ABedroomCount: Integer;
    ALivingRoomCount: Integer;
    AKitchenCount: Integer;
    AWashroomCount: Integer;
    ABalconyCount: Integer;
    APropMgtTypeId: Integer;
    ADirectionId: Integer;
    ADecorationId: Integer;
    ATotalFloor: Integer;
    AFloor: Integer;
    AConstructYearId: Integer;
    ATransport: string;
    AEnvironment: string;
    ARemark: string;
    ARentHouseId: Integer;
    ASecondHandHouseId: Integer;
  public
    [TFieldInfo('ID', '���')]
    property Id: Integer read AId write AId;
    [TFieldInfo('NAME', '¥������')]
    property Name: string read AName write AName;
    [TFieldInfo('PROVINCE_ID', 'ʡ����������ֱϽ��')]
    property ProvinceId: Integer read AProvinceId write AProvinceId;
    [TFieldInfo('CITY_ID', '����')]
    property CityId: Integer read ACityId write ACityId;
    [TFieldInfo('DISTRICT_ID', '�����ء���')]
    property DistrictId: Integer read ADistrictId write ADistrictId;
    [TFieldInfo('ADDRESS', '��ϸ��ַ')]
    property Address: string read AAddress write AAddress;
    [TFieldInfo('BEDROOM_COUNT', '��')]
    property BedroomCount: Integer read ABedroomCount write ABedroomCount;
    [TFieldInfo('LIVING_ROOM_COUNT', '��')]
    property LivingRoomCount: Integer read ALivingRoomCount write ALivingRoomCount;
    [TFieldInfo('KITCHEN_COUNT', '��')]
    property KitchenCount: Integer read AKitchenCount write AKitchenCount;
    [TFieldInfo('WASHROOM_COUNT', '��')]
    property WashroomCount: Integer read AWashroomCount write AWashroomCount;
    [TFieldInfo('BALCONY_COUNT', '��̨')]
    property BalconyCount: Integer read ABalconyCount write ABalconyCount;
    [TFieldInfo('PROP_MGT_TYPE_ID', '��ҵ����')]
    property PropMgtTypeId: Integer read APropMgtTypeId write APropMgtTypeId;
    [TFieldInfo('DIRECTION_ID', '����')]
    property DirectionId: Integer read ADirectionId write ADirectionId;
    [TFieldInfo('DECORATION_ID', 'װ�޳̶�')]
    property DecorationId: Integer read ADecorationId write ADecorationId;
    [TFieldInfo('TOTAL_FLOOR', '��¥��')]
    property TotalFloor: Integer read ATotalFloor write ATotalFloor;
    [TFieldInfo('FLOOR', '����¥��')]
    property Floor: Integer read AFloor write AFloor;
    [TFieldInfo('CONSTRUCT_YEAR_ID', '�������')]
    property ConstructYearId: Integer read AConstructYearId write AConstructYearId;
    [TFieldInfo('TRANSPORT', '��ͨ״��')]
    property Transport: string read ATransport write ATransport;
    [TFieldInfo('ENVIRONMENT', '�ܱ�����')]
    property Environment: string read AEnvironment write AEnvironment;
    [TFieldInfo('REMARK', '��Դ����')]
    property Remark: string read ARemark write ARemark;
    [TFieldInfo('RENT_HOUSE_ID', '���ⷿ')]
    property RentHouseId: Integer read ARentHouseId write ARentHouseId;
    [TFieldInfo('SECOND_HAND_HOUSE_ID', '���ַ�')]
    property SecondHandHouseId: Integer read ASecondHandHouseId write ASecondHandHouseId;
  end;

implementation

end.