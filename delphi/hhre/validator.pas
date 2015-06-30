unit validator;

interface

uses
  StdCtrls, SysUtils, Dialogs, constant, RegularExpressionsCore, Graphics, Windows;

type
  TValidator = class
  strict private
    constructor Create;
  public
    //��ֵ��֤
    class procedure ValidEmptyStr(const control: TEdit; const name: string);
    //������ʽ��֤
    class procedure ValidRegExpr(const control: TEdit; const name: string; const regExpr: string);
    //��λС����֤
    class procedure ValidTwoDecimals(const control: TEdit; const name: string);
    //��λ������֤
    class procedure ValidTwoFigures(const control: TEdit; const name: string);
    //������֤
    class procedure ValidFigures(const control: TEdit; const name: string);
    //��λ������֤
    { TODO -oAnders : ��Ҫ���벻������0 }
    class procedure ValidThreeFigures(const control: TEdit; const name: string);
    //���������ؼ���֤��A>=B��
    class procedure ValidTwoIntGreat(const greatControl, smallControl: TEdit; const greatName, smallName: string);
    //���������ؼ���֤��A<=B��
    class procedure ValidTwoIntSmall(const smallControl, greatControl: TEdit; const smallName, greatName: string);
  end;

implementation

constructor TValidator.Create;
begin
  inherited;
end;

class procedure TValidator.ValidEmptyStr(const control: TEdit; const name: string);
begin
  if CompareStr(Trim(control.Text), EmptyStr) = 0 then
  begin
    control.Color := clRed;
    MessageBox(0, PChar(name + ERROR_EMPTY_STR), BTN_OK ,MB_OK);
    control.SetFocus;
    Abort;
  end
  else
  begin
    control.Color := clWindow;
  end;
end;

class procedure TValidator.ValidFigures(const control: TEdit; const name: string);
begin
  ValidRegExpr(control, name, REG_FIGURES);
end;

class procedure TValidator.ValidRegExpr(const control: TEdit; const name: string; const regExpr: string);
var
  reg: TPerlRegEx;
begin
  reg := TPerlRegEx.Create;
  reg.Subject := Trim(control.Text);
  reg.RegEx := regExpr;

  if not reg.Match then
  begin
    control.Color := clRed;
    MessageBox(0, PChar(name + ERROR_REG_EXPR), BTN_OK ,MB_OK);
    control.SetFocus;
    Abort;
  end
  else
  begin
    control.Color := clWindow;
  end;
end;

class procedure TValidator.ValidThreeFigures(const control: TEdit; const name: string);
begin
  ValidRegExpr(control, name, REG_THREE_FIGURES);
end;

class procedure TValidator.ValidTwoIntGreat(const greatControl, smallControl: TEdit; const greatName, smallName: string);
begin
  if StrToInt(Trim(greatControl.Text)) < StrToInt(Trim(smallControl.Text)) then
  begin
    greatControl.Color := clRed;
    MessageBox(0, PChar(greatName + Format(ERROR_TWO_VALUE_GREAT, [greatName, smallName])), BTN_OK, MB_OK);
    greatControl.SetFocus;
    Abort;
  end
  else
  begin
    greatControl.Color := clWindow;
  end;
end;

class procedure TValidator.ValidTwoIntSmall(const smallControl, greatControl: TEdit; const smallName, greatName: string);
begin
  if StrToInt(Trim(smallControl.Text)) > StrToInt(Trim(greatControl.Text)) then
  begin
    smallControl.Color := clRed;
    MessageBox(0, PChar(smallName + Format(ERROR_TWO_VALUE_SMALL, [smallName, greatName])), BTN_OK ,MB_OK);
    smallControl.SetFocus;
    Abort;
  end
  else
  begin
    smallControl.Color := clWindow;
  end;
end;

class procedure TValidator.ValidTwoDecimals(const control: TEdit; const name: string);
begin
  ValidRegExpr(control, name, REG_TWO_DECIMALS);
end;

class procedure TValidator.ValidTwoFigures(const control: TEdit; const name: string);
begin
  ValidRegExpr(control, name, REG_TWO_FIGURES);
end;

end.
