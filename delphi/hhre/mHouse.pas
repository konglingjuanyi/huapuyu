unit mHouse;

interface

uses
  orm;

type
  [TTableInfo('TB_HOUSE', '房屋')]
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
    [TFieldInfo('ID', '编号')]
    property Id: Integer read AId write AId;
    [TFieldInfo('NAME', '楼盘名称')]
    property Name: string read AName write AName;
    [TFieldInfo('PROVINCE_ID', '省、自治区、直辖市')]
    property ProvinceId: Integer read AProvinceId write AProvinceId;
    [TFieldInfo('CITY_ID', '城市')]
    property CityId: Integer read ACityId write ACityId;
    [TFieldInfo('DISTRICT_ID', '区、县、市')]
    property DistrictId: Integer read ADistrictId write ADistrictId;
    [TFieldInfo('ADDRESS', '详细地址')]
    property Address: string read AAddress write AAddress;
    [TFieldInfo('BEDROOM_COUNT', '室')]
    property BedroomCount: Integer read ABedroomCount write ABedroomCount;
    [TFieldInfo('LIVING_ROOM_COUNT', '厅')]
    property LivingRoomCount: Integer read ALivingRoomCount write ALivingRoomCount;
    [TFieldInfo('KITCHEN_COUNT', '厨')]
    property KitchenCount: Integer read AKitchenCount write AKitchenCount;
    [TFieldInfo('WASHROOM_COUNT', '卫')]
    property WashroomCount: Integer read AWashroomCount write AWashroomCount;
    [TFieldInfo('BALCONY_COUNT', '阳台')]
    property BalconyCount: Integer read ABalconyCount write ABalconyCount;
    [TFieldInfo('PROP_MGT_TYPE_ID', '物业类型')]
    property PropMgtTypeId: Integer read APropMgtTypeId write APropMgtTypeId;
    [TFieldInfo('DIRECTION_ID', '朝向')]
    property DirectionId: Integer read ADirectionId write ADirectionId;
    [TFieldInfo('DECORATION_ID', '装修程度')]
    property DecorationId: Integer read ADecorationId write ADecorationId;
    [TFieldInfo('TOTAL_FLOOR', '总楼层')]
    property TotalFloor: Integer read ATotalFloor write ATotalFloor;
    [TFieldInfo('FLOOR', '所在楼层')]
    property Floor: Integer read AFloor write AFloor;
    [TFieldInfo('CONSTRUCT_YEAR_ID', '建筑年代')]
    property ConstructYearId: Integer read AConstructYearId write AConstructYearId;
    [TFieldInfo('TRANSPORT', '交通状况')]
    property Transport: string read ATransport write ATransport;
    [TFieldInfo('ENVIRONMENT', '周边配套')]
    property Environment: string read AEnvironment write AEnvironment;
    [TFieldInfo('REMARK', '房源描述')]
    property Remark: string read ARemark write ARemark;
    [TFieldInfo('RENT_HOUSE_ID', '出租房')]
    property RentHouseId: Integer read ARentHouseId write ARentHouseId;
    [TFieldInfo('SECOND_HAND_HOUSE_ID', '二手房')]
    property SecondHandHouseId: Integer read ASecondHandHouseId write ASecondHandHouseId;
  end;

implementation

end.
