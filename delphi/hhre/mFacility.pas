unit mFacility;

interface

uses
  orm;

type
  [TTableInfo('TB_FACILITY', '������ʩ')]
  TFacility = class(TModel)
  strict private
    AHouseId: Integer;
    AFacilityId: Integer;
  public
    [TFieldInfo('HOUSE_ID', '���ݱ��')]
    property HouseId: Integer read AHouseId write AHouseId;
    [TFieldInfo('FACILITY_ID', '������ʩ���')]
    property FacilityId: Integer read AFacilityId write AFacilityId;
  end;

implementation

end.
