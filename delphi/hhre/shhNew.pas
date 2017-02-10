unit shhNew;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, DB, DBClient, Generics.Collections, config, sql, common,
  CheckLst, RegularExpressionsCore, constant, validator, sqlScript, mHouse,
  mSecondHandHouse, mFacility, mFeature;

type
  TFrmNew = class(TForm)
    gbDetailInfo: TGroupBox;
    Label21: TLabel;
    Label23: TLabel;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    cmbDecoration: TComboBox;
    cmbConstructYear: TComboBox;
    cmbPropType: TComboBox;
    cmbConstructType: TComboBox;
    cmbPropStruct: TComboBox;
    gbEssentialInfo: TGroupBox;
    Label27: TLabel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label22: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label30: TLabel;
    Label6: TLabel;
    Label9: TLabel;
    edtName: TEdit;
    cmbProvince: TComboBox;
    cmbCity: TComboBox;
    cmbDistrict: TComboBox;
    edtPrice: TEdit;
    edtUnitPrice: TEdit;
    edtBuildingArea: TEdit;
    edtAddress: TEdit;
    edtBedroomCount: TEdit;
    edtLivingRoomCount: TEdit;
    edtKitchenCount: TEdit;
    edtWashroomCount: TEdit;
    edtUsableArea: TEdit;
    cmbDirection: TComboBox;
    edtTotalFloor: TEdit;
    edtFloor: TEdit;
    cmbProperty: TComboBox;
    edtBalconyCount: TEdit;
    Label31: TLabel;
    cmbPropMgtType: TComboBox;
    Label32: TLabel;
    Label33: TLabel;
    Label34: TLabel;
    Label35: TLabel;
    Label36: TLabel;
    Label37: TLabel;
    Label38: TLabel;
    cmbVisitTime: TComboBox;
    clbFacility: TCheckListBox;
    clbFeature: TCheckListBox;
    memTransport: TMemo;
    memEnvironment: TMemo;
    memRemark: TMemo;
    Label39: TLabel;
    Label40: TLabel;
    btnNew: TButton;
    procedure FormShow(Sender: TObject);
    procedure cmbProvinceChange(Sender: TObject);
    procedure cmbCityChange(Sender: TObject);
    procedure btnNewClick(Sender: TObject);
    procedure exitControl(Sender: TObject);
  private
    procedure Validate(Sender: TWinControl);
  public
    { Public declarations }
  end;

var
  FrmNew: TFrmNew;

implementation

{$R *.dfm}

procedure TFrmNew.btnNewClick(Sender: TObject);
var
  house: THouse;
  secondHandHouse: TSecondHandHouse;
  facility: TFacility;
  feature: TFeature;
  i: Integer;
begin
  TValidator.ValidEmptyStr(edtName, BUILDING_NAME);
  TValidator.ValidEmptyStr(edtAddress, ADDRESS);
  TValidator.ValidEmptyStr(edtPrice, PRICE);
  TValidator.ValidEmptyStr(edtBuildingArea, BUILDING_AREA);
  TValidator.ValidEmptyStr(edtUsableArea, USABLE_AREA);
  TValidator.ValidEmptyStr(edtBedroomCount, BEDROOM);
  TValidator.ValidEmptyStr(edtLivingRoomCount, LIVING_ROOM);
  TValidator.ValidEmptyStr(edtKitchenCount, KITCHEN);
  TValidator.ValidEmptyStr(edtWashroomCount, WASHROOM);
  TValidator.ValidEmptyStr(edtBalconyCount, BALCONY);
  TValidator.ValidEmptyStr(edtTotalFloor, TOTAL_FLOOR);
  TValidator.ValidEmptyStr(edtFloor, FLOOR);

  secondHandHouse := TSecondHandHouse.Create;
  secondHandHouse.Price := StrToFloat(Trim(edtPrice.Text));
  secondHandHouse.BuildingArea := StrToFloat(Trim(edtBuildingArea.Text));
  secondHandHouse.UsableArea := StrToFloat(Trim(edtUsableArea.Text));
  secondHandHouse.PropertyId := Integer(cmbProperty.Items.Objects[cmbProperty.ItemIndex]);
  secondHandHouse.PropTypeId := Integer(cmbPropType.Items.Objects[cmbPropType.ItemIndex]);
  secondHandHouse.PropStructId := Integer(cmbPropStruct.Items.Objects[cmbPropStruct.ItemIndex]);
  secondHandHouse.ConstructTypeId := Integer(cmbConstructType.Items.Objects[cmbConstructType.ItemIndex]);
  secondHandHouse.VisitTimeId := Integer(cmbVisitTime.Items.Objects[cmbVisitTime.ItemIndex]);

  ShowMessage(secondHandHouse.Insert);

  house := THouse.Create;
  house.Name := Trim(edtName.Text);
  house.ProvinceId := Integer(cmbProvince.Items.Objects[cmbProvince.ItemIndex]);
  house.CityId := Integer(cmbCity.Items.Objects[cmbCity.ItemIndex]);
  house.DistrictId := Integer(cmbDistrict.Items.Objects[cmbDistrict.ItemIndex]);
  house.Address := Trim(edtAddress.Text);
  house.BedroomCount := StrToInt(Trim(edtBedroomCount.Text));
  house.LivingRoomCount := StrToInt(Trim(edtLivingRoomCount.Text));
  house.KitchenCount := StrToInt(Trim(edtKitchenCount.Text));
  house.WashroomCount := StrToInt(Trim(edtWashroomCount.Text));
  house.BalconyCount := StrToInt(Trim(edtBalconyCount.Text));
  house.PropMgtTypeId := Integer(cmbPropMgtType.Items.Objects[cmbPropMgtType.ItemIndex]);
  house.DirectionId := Integer(cmbDirection.Items.Objects[cmbDirection.ItemIndex]);
  house.DecorationId := Integer(cmbDecoration.Items.Objects[cmbDecoration.ItemIndex]);
  house.TotalFloor := StrToInt(Trim(edtTotalFloor.Text));
  house.Floor := StrToInt(Trim(edtFloor.Text));
  house.ConstructYearId := Integer(cmbConstructYear.Items.Objects[cmbConstructYear.ItemIndex]);
  house.Transport := Trim(memTransport.Text);
  house.Environment := Trim(memEnvironment.Text);
  house.Remark := Trim(memRemark.Text);
  house.SecondHandHouseId := secondHandHouse.Id;

  ShowMessage(house.Insert);

  facility := TFacility.Create;
  facility.HouseId := house.Id;
  for i := 0 to clbFacility.Items.Count - 1 do
  begin
    if clbFacility.Checked[i] then
    begin
      facility.FacilityId := Integer(clbFacility.Items.Objects[i]);
      facility.InsertWithoutId;
    end;
  end;

  feature := TFeature.Create;
  feature.HouseId := house.Id;
  for i := 0 to clbFeature.Items.Count - 1 do
  begin
    if clbFeature.Checked[i] then
    begin
      feature.FeatureId := Integer(clbFeature.Items.Objects[i]);
      feature.InsertWithoutId;
    end;
  end;
end;

procedure TFrmNew.cmbCityChange(Sender: TObject);
begin
  TCommon.GenComboBoxItem(TSqlScript.GetDistrict(IntToStr(Integer(cmbCity.Items.Objects[cmbCity.ItemIndex]))), cmbDistrict);
end;

procedure TFrmNew.cmbProvinceChange(Sender: TObject);
begin
  TCommon.GenComboBoxItem(TSqlScript.GetCity(IntToStr(Integer(cmbProvince.Items.Objects[cmbProvince.ItemIndex]))), cmbCity);
  TCommon.GenComboBoxItem(TSqlScript.GetDistrict(IntToStr(Integer(cmbCity.Items.Objects[cmbCity.ItemIndex]))), cmbDistrict);
end;

procedure TFrmNew.exitControl(Sender: TObject);
begin
  if Sender is TEdit then
    Validate(Sender as TEdit);
end;

//��ʼ���ؼ�����
procedure TFrmNew.FormShow(Sender: TObject);
begin
  //��������
  TCommon.GenComboBoxItem(TSqlScript.GetProvince, cmbProvince);
  TCommon.GenComboBoxItem(TSqlScript.GetCity(IntToStr(Integer(cmbProvince.Items.Objects[cmbProvince.ItemIndex]))), cmbCity);
  TCommon.GenComboBoxItem(TSqlScript.GetDistrict(IntToStr(Integer(cmbCity.Items.Objects[cmbCity.ItemIndex]))), cmbDistrict);
  TCommon.GenComboBoxItem(TSqlScript.GetDirection, cmbDirection);
  TCommon.GenComboBoxItem(TSqlScript.GetPropety, cmbProperty);

  //��ϸ����
  TCommon.ClearMemoLines(gbDetailInfo);
  TCommon.GenComboBoxItem(TSqlScript.GetPropMgtType, cmbPropMgtType);
  TCommon.GenComboBoxItem(TSqlScript.GetPropType, cmbPropType);
  TCommon.GenComboBoxItem(TSqlScript.GetPropStruct, cmbPropStruct);
  TCommon.GenComboBoxItem(TSqlScript.GetDecoration, cmbDecoration);
  TCommon.GenComboBoxItem(TSqlScript.GetConstructType, cmbConstructType);
  TCommon.GenComboBoxItem(TSqlScript.GetConstructYear, cmbConstructYear);
  TCommon.GenComboBoxItem(TSqlScript.GetVisitTime, cmbVisitTime);
  TCommon.GenCheckListBoxItem(TSqlScript.GetFacility, clbFacility);
  TCommon.GenCheckListBoxItem(TSqlScript.GetFeature, clbFeature);
end;

//Ч��
procedure TFrmNew.Validate(Sender: TWinControl);
begin
  case Sender.TabOrder of
    //¥������
    0:
    begin
      TValidator.ValidEmptyStr(edtName, BUILDING_NAME);
    end;
    //��ϸ��ַ
    4:
    begin
      TValidator.ValidEmptyStr(edtAddress, ADDRESS);
    end;
    //�ۼ�
    5:
    begin
      TValidator.ValidEmptyStr(edtPrice, PRICE);
      TValidator.ValidTwoDecimals(edtPrice, PRICE);
      if TCommon.ControlValueIsNotEmpty(edtBuildingArea) then
        edtUnitPrice.Text := FormatFloat(FORMAT_TWO_DECIMALS, StrToFloat(Trim(edtPrice.Text)) * FORMAT_TEN_THOUSAND / StrToFloat(Trim(edtBuildingArea.Text)));
    end;
    //�������
    6:
    begin
      TValidator.ValidEmptyStr(edtBuildingArea, BUILDING_AREA);
      TValidator.ValidTwoDecimals(edtBuildingArea, BUILDING_AREA);
      if TCommon.ControlValueIsNotEmpty(edtPrice) then
        edtUnitPrice.Text := FormatFloat(FORMAT_TWO_DECIMALS, StrToFloat(Trim(edtPrice.Text)) * FORMAT_TEN_THOUSAND / StrToFloat(Trim(edtBuildingArea.Text)));
    end;
    //ʹ�����
    7:
    begin
      TValidator.ValidEmptyStr(edtUsableArea, USABLE_AREA);
      TValidator.ValidTwoDecimals(edtUsableArea, USABLE_AREA);
    end;
    //��
    9:
    begin
      TValidator.ValidEmptyStr(edtBedroomCount, BEDROOM);
      TValidator.ValidTwoFigures(edtBedroomCount, BEDROOM);
    end;
    //��
    10:
    begin
      TValidator.ValidEmptyStr(edtLivingRoomCount, LIVING_ROOM);
      TValidator.ValidTwoFigures(edtLivingRoomCount, LIVING_ROOM);
    end;
    //��
    11:
    begin
      TValidator.ValidEmptyStr(edtKitchenCount, KITCHEN);
      TValidator.ValidTwoFigures(edtKitchenCount, KITCHEN);
    end;
    //��
    12:
    begin
      TValidator.ValidEmptyStr(edtWashroomCount, WASHROOM);
      TValidator.ValidTwoFigures(edtWashroomCount, WASHROOM);
    end;
    //��̨
    13:
    begin
      TValidator.ValidEmptyStr(edtBalconyCount, BALCONY);
      TValidator.ValidTwoFigures(edtBalconyCount, BALCONY);
    end;
    //��¥��
    15:
    begin
      TValidator.ValidEmptyStr(edtTotalFloor, TOTAL_FLOOR);
      TValidator.ValidThreeFigures(edtTotalFloor, TOTAL_FLOOR);
      if TCommon.ControlValueIsNotEmpty(edtFloor) then
        TValidator.ValidTwoIntGreat(edtTotalFloor, edtFloor, TOTAL_FLOOR, FLOOR);
    end;
    //����¥��
    16:
    begin
      TValidator.ValidEmptyStr(edtFloor, FLOOR);
      TValidator.ValidThreeFigures(edtFloor, FLOOR);
      if TCommon.ControlValueIsNotEmpty(edtTotalFloor) then
        TValidator.ValidTwoIntSmall(edtFloor, edtTotalFloor, FLOOR, TOTAL_FLOOR);
    end;
  end;
end;

end.
