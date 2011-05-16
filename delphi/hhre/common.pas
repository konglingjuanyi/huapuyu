unit common;

interface

uses
  StdCtrls, SysUtils, Generics.Collections, CheckLst, Dialogs, sql, config, constant, Controls, DBGrids,
  DBClient, Classes;

type
  TCommon = class
  strict private
    constructor Create;
  public
    class procedure GenComboBoxItem(const sql: string; const cmb: TComboBox);
    class procedure GenCheckListBoxItem(const sql: string; const clb: TCheckListBox);
    class function ControlValueIsEmpty(const control: TEdit): Boolean;
    class function ControlValueIsNotEmpty(const control: TEdit): Boolean;
    class procedure ClearMemoLines(const container: TWinControl);
    class procedure AutoCfgDBGridSize(const cds: TClientDataSet; const dbg: TDBGrid);
  end;

implementation

class procedure TCommon.AutoCfgDBGridSize(const cds: TClientDataSet; const dbg: TDBGrid);
var
  col: TColumn;
  colWidth: Integer;
  modInt: Integer;
  i: Integer;
begin
  dbg.Columns.Clear;

  for i := 0 to cds.Fields.Count - 1 do
  begin
    if SameStr(cds.Fields[i].FieldName, PRIMARY_KEY_NAME) then
      Continue;

    col := dbg.Columns.Add;
    col.FieldName := cds.Fields[i].FieldName;
    col.Title.Alignment := taCenter;
    col.Alignment := taCenter;
    //������ʽ����߿��ұ߿�ʹ�ֱ������һ��20���أ�XP��ʽ����߿��ұ߿�ʹ�ֱ������һ��21���أ�
    //����֮����1���أ�n�о���n-1���أ���˵ó�����ļ��㹫ʽwidth-21-(n-1)
    colWidth := (dbg.Width - 21 - (cds.FieldCount - 2)) div (cds.FieldCount - 1);
    modInt := (dbg.Width - 21 - (cds.FieldCount - 2)) mod (cds.FieldCount - 1);

    if (i = cds.Fields.Count - 1) and (modInt <> 0) then
      col.Width := colWidth + modInt
    else
      col.Width := colWidth;
  end;
end;

class procedure TCommon.ClearMemoLines(const container: TWinControl);
var
  i: Integer;
begin
  for i := 0 to container.ControlCount - 1 do
  begin
    if container.Controls[i].ClassNameIs(TMemo.ClassName) then
    begin
      (container.Controls[i] as TMemo).Lines.Clear;
    end;
  end;
end;

class function TCommon.ControlValueIsEmpty(const control: TEdit): Boolean;
begin
  if CompareStr(Trim(control.Text), EmptyStr) = 0 then
    Result := True
  else
    Result := False;
end;

class function TCommon.ControlValueIsNotEmpty(const control: TEdit): Boolean;
begin
  if CompareStr(Trim(control.Text), EmptyStr) <> 0 then
    Result := True
  else
    Result := False;
end;

constructor TCommon.Create;
begin
  inherited;
end;

class procedure TCommon.GenCheckListBoxItem(const sql: string; const clb: TCheckListBox);
var
  ds: TDictionary<Integer, string>;
  it: TDictionary<Integer, string>.TPairEnumerator;
begin
  clb.Items.Clear;
  ds := TSql.ExecuteQueryMap(sql);
  it := ds.GetEnumerator;
  while it.MoveNext do
  begin
    clb.AddItem(it.Current.Value, TObject(it.Current.Key));
  end;
end;

class procedure TCommon.GenComboBoxItem(const sql: string; const cmb: TComboBox);
var
  ds: TDictionary<Integer, string>;
  it: TDictionary<Integer, string>.TPairEnumerator;
  i: Integer;
  selectedIndex: Integer;
begin
  cmb.Items.Clear;
  ds := TSql.ExecuteQueryMap(sql);
  it := ds.GetEnumerator;
  i := 0;
  selectedIndex := 0;
  while it.MoveNext do
  begin
    cmb.AddItem(it.Current.Value, TObject(it.Current.Key));
    if (CompareStr(it.Current.Value, TConfig.GetCustomizeProvince) = 0) or (CompareStr(it.Current.Value, TConfig.GetCustomizeCity) = 0) then
      selectedIndex := i;
    Inc(i);
  end;

  cmb.ItemIndex := selectedIndex;
end;

end.
