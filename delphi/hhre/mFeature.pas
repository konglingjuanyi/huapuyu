unit mFeature;

interface

uses
  orm;

type
  [TTableInfo('TB_FEATURE', '��Դ��ɫ')]
  TFeature = class(TModel)
  strict private
    AHouseId: Integer;
    AFeatureId: Integer;
  public
    [TFieldInfo('HOUSE_ID', '���ݱ��')]
    property HouseId: Integer read AHouseId write AHouseId;
    [TFieldInfo('FEATURE_ID', '��Դ��ɫ���')]
    property FeatureId: Integer read AFeatureId write AFeatureId;
  end;

implementation

end.
