import { Tool_ObjUtils } from "../Tools/Tool_ObjUtils";
import { MyMouseEventStarling } from "../MyView/MyMouseEventStarling";
import { MyGesture } from "./MyGesture";
import { Tool_Function } from "../Tools/Tool_Function";
import { Tool_ImgUtils } from "../Tools/Tool_ImgUtils";
import { Tool_SpriteUtils } from "../Tools/Tool_SpriteUtils";
import { LayerManager } from "../MyView/LayerManager";
import { Image } from "../../../starling/Image";

export class MyGesture_DragAndCopy extends MyGesture{
    public copyTar:any;
	public startDragNeedX:number =0;
	public startDragNeedY:number =0;
	private isdrag:boolean=false;
	private saveX:number=0;
	private saveY:number=0;
	private startX:number=0;
	private startY:number=0;
	private gp:Laya.Point=new  Laya.Point();
	//自身lp范围
	private recSelf:any;
	private isDown:boolean=false;
	//移动的距离
	private lastXY:any ={};
	public movedXY:any =Tool_ObjUtils.getNewObjectFromPool("x",0,"y",0);
	private MME:MyMouseEventStarling;
	
	constructor(tar:any,f:any,val:any){
		super(tar,f,val);
		this.recSelf = tar.getSelfBounds();
//		var MME:MyMouseEventStarling	= addMME();
		this.MME	= new MyMouseEventStarling(LayerManager.instance);
		this.MME.setValue("down事件",this.onDownF.bind(this));
		this.Arr_MME.push(this.MME);
		this.MME.setValue("_开始移动距离",1);
		this.MME.setValue("滑动",this.slideF.bind(this));
	}
	public  changeSelfPosition():void{		
	}
	private  onDownF(p:any):void{		
		if(this.isDown==true || this.pause==true)return;	
		this.gp.x = p.x;
		this.gp.y = p.y;
		this.gp =(this.Tar as Image).globalToLocal(this.gp);
		if(this.gp.x>this.recSelf.x && this.gp.x<this.recSelf.width && this.gp.y>this.recSelf.y && this.gp.y<this.recSelf.height){
			this.isDown=true;
			this.lastXY.x =this. MME.worldXY.x;
			this.lastXY.y =this.MME.worldXY.y;
		}
	}
	
	private  slideF(dic:any):void
	{

		if(this.pause || this.isDown==false)return;
		if(dic["类型"] == "移动")
		{
			if(this.isdrag==false){
				if(this.startX==0){
					var lp:any = this.Tar.globalToLocal(new Laya.Point(this.Arr_MME[0].worldXY.x,this.Arr_MME[0].worldXY.y));
					this.startX =lp.x;
					this.startY =lp.y;
				}
				if(this.startDragNeedX != 0){
					this.saveY+=dic['y'];
					if(dic["x"] <= 0 && this.startDragNeedX>0){
						this.saveX=0;
						return;
					}
					if(dic["x"] >= 0 && this.startDragNeedX<0){
						this.saveX=0;
						return;
					}
					if(Math.abs(dic["y"]) > Math.abs(dic["x"])){
						return;
					}
					this.saveX+=dic["x"];
					if(Math.abs(this.saveX)<Math.abs(this.startDragNeedX)){
						return;
					}
					if(Math.abs(this.saveX)<Math.abs(this.saveY)){
						return;
					}
				}else if(this.startDragNeedY != 0){
					if(dic["y"] <= 0 && this.startDragNeedY>0){return;}
					if(dic["y"] >= 0 && this.startDragNeedY<0){return;}
					if(Math.abs(dic["x"]) > Math.abs(dic["y"])){
						return;
					}
				}
				this.isdrag=true;
				this.movedXY.x=0;
				this.movedXY.y=0;
			}
			if(this.copyTar==null){
				if( this.Tar instanceof Image){
					this.copyTar=Tool_ImgUtils.cloneImage(this.Tar);
				}else{
					this.copyTar=Tool_SpriteUtils.cloneSprite(this.Tar);
				}
				this.copyTar.x =this.MME.worldXY.x-this.startX;
				this.copyTar.y =this.MME.worldXY.y-this.startY;
				this.Tar.visible=false;
				LayerManager.instance.LayerTop.addChild(this.copyTar);
				if(this.FValue!=null)	Tool_Function.onRunFunction(this.F,"开始移动",this.FValue);
				else					Tool_Function.onRunFunction(this.F,"开始移动");
			}else{
				this.copyTar.x+=this.MME.worldXY.x - this.lastXY.x;
				this.copyTar.y+=this.MME.worldXY.y - this.lastXY.y;
				this.movedXY.x +=this.MME.worldXY.x -this. lastXY.x;
				this.movedXY.y +=this.MME.worldXY.y -this. lastXY.y;
			}
			this.lastXY.x = this.MME.worldXY.x;
			this.lastXY.y =this.MME.worldXY.y;
		}else{
			this.Tar.visible=true;
			this.startX=0;
			this.saveX=this.saveY=0;
			this.isDown=false;
			if(this.isdrag==true){
				this.isdrag=false;
				if(this.FValue!=null)	Tool_Function.onRunFunction(this.F,"停止",this.FValue,(this.Arr_MME[0]as MyMouseEventStarling).worldXY);
				else				Tool_Function.onRunFunction(this.F,"停止",(this.Arr_MME[0]as MyMouseEventStarling).worldXY);
			}else{
				if(this.FValue!=null)	Tool_Function.onRunFunction(this.F,"点击",this.FValue);
				else					Tool_Function.onRunFunction(this.F,"点击");
			}
			this.copyTar=Tool_ObjUtils.destroyF_One(this.copyTar);
		}
	}
	public  stopDrag():void{
		this.startX=0;
		this.isDown=false;
		var lastPause:boolean=this.pause;
		this.pause=true;
		this.isdrag=false;
		this.saveX=this.saveY=0;
		(this.Arr_MME[0]as MyMouseEventStarling).setValue("停止本次");
		this.pause=lastPause;
		this.Tar.visible=true;
		this.copyTar=Tool_ObjUtils.destroyF_One(this.copyTar);
	}
	
 public  destroyF():void
	{
		this.movedXY=Tool_ObjUtils.destroyF_One(this.movedXY);
		this.copyTar=Tool_ObjUtils.destroyF_One(this.copyTar);
		super.destroyF();
	}
}