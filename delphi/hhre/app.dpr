program app;

uses
  Forms,
  main in 'main.pas' {FrmMain},
  shh in 'shh.pas' {FrmShh},
  about in 'about.pas' {FrmAbout},
  buyer in 'buyer.pas' {FrmBuyer},
  seller in 'seller.pas' {FrmSeller},
  shhNew in 'shhNew.pas' {FrmNew},
  config in 'config.pas',
  constant in 'constant.pas',
  common in 'common.pas',
  validator in 'validator.pas',
  sqlScript in 'sqlScript.pas',
  stringBuilder in 'stringBuilder.pas';

{$R *.RES}

begin
  Application.Initialize;
  Application.Title := '���������н�������';
  Application.CreateForm(TfrmMain, frmMain);
  Application.CreateForm(TfrmAbout, frmAbout);
  Application.Run;
end.
