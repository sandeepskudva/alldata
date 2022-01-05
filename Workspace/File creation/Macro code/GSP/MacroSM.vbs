Set objExcel = CreateObject("Excel.Application")
Set objWorkbook = objExcel.Workbooks.Open("E:\TSG\Workspace\File creation\Macro code\GSP\Smart File Creator.xlsm")

objExcel.Application.Run "'Smart File Creator.xlsm'!PopulateData" 
objWorkbook.Save
objExcel.ActiveWorkbook.Close

objExcel.Application.Quit
WScript.Quit