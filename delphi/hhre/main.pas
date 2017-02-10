unit main;

interface

uses Windows, SysUtils, Classes, Graphics, Forms, Controls, Menus,
  StdCtrls, Dialogs, Buttons, Messages, ExtCtrls, ComCtrls, StdActns,
  ActnList, ToolWin, ImgList, shh, about;

type
  TFrmMain = class(TForm)
    mmMain: TMainMenu;

    itemRent: TMenuItem; // ��������
    itemLessor: TMenuItem; // �����˹���
    itemTenant: TMenuItem; // �����˹���
    itemHouse: TMenuItem; // ���ⷿ����

    itemBargain: TMenuItem; // ��������
    itemSeller: TMenuItem; // ���ҹ���
    itemBuyer: TMenuItem; // ��ҹ���
    itemShh: TMenuItem; // ���ַ�����

    itemSetting: TMenuItem; // ϵͳ����
    N1: TMenuItem;
    itemExit: TMenuItem; // �˳�ϵͳ

    itemHelp: TMenuItem; // ����
    itemAbout: TMenuItem; // ����

    sbMain: TStatusBar;

    procedure itemAboutClick(Sender: TObject);
    procedure itemExitClick(Sender: TObject);
    procedure itemShhClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmMain: TFrmMain;
  //frmShh: TFrmShh;

implementation

{$R *.dfm}

// �˳�ϵͳ
procedure TFrmMain.itemExitClick(Sender: TObject);
begin
  Close;
end;

// ����
procedure TFrmMain.itemAboutClick(Sender: TObject);
begin
  frmAbout.ShowModal;
end;

// ���ַ�����
procedure TFrmMain.itemShhClick(Sender: TObject);
begin
  //frmShh := TFrmShh.Create(Application);
  TFrmShh.Create(Application);
end;

end.
