unit main;

interface

uses Windows, SysUtils, Classes, Graphics, Forms, Controls, Menus,
  StdCtrls, Dialogs, Buttons, Messages, ExtCtrls, ComCtrls, StdActns,
  ActnList, ToolWin, ImgList, shh, about;

type
  TFrmMain = class(TForm)
    mmMain: TMainMenu;

    itemRent: TMenuItem; // 房屋租赁
    itemLessor: TMenuItem; // 出租人管理
    itemTenant: TMenuItem; // 承租人管理
    itemHouse: TMenuItem; // 出租房管理

    itemBargain: TMenuItem; // 房屋买卖
    itemSeller: TMenuItem; // 卖家管理
    itemBuyer: TMenuItem; // 买家管理
    itemShh: TMenuItem; // 二手房管理

    itemSetting: TMenuItem; // 系统设置
    N1: TMenuItem;
    itemExit: TMenuItem; // 退出系统

    itemHelp: TMenuItem; // 帮助
    itemAbout: TMenuItem; // 关于

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

// 退出系统
procedure TFrmMain.itemExitClick(Sender: TObject);
begin
  Close;
end;

// 关于
procedure TFrmMain.itemAboutClick(Sender: TObject);
begin
  frmAbout.ShowModal;
end;

// 二手房管理
procedure TFrmMain.itemShhClick(Sender: TObject);
begin
  //frmShh := TFrmShh.Create(Application);
  TFrmShh.Create(Application);
end;

end.
