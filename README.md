# SpringCloud学习代码

视频地址：https://www.bilibili.com/video/av45446255?from=search&seid=2359342928608476307

Consul服务停止之后并未消失，删除Consul的data_dir中的内容即可
<br/>
Consul启动命令：<br>
nohup ./consul agent -data-dir=data_dir/ -node=node0 -bind=192.168.56.101 -datacenter=consul-server -ui -client=192.168.56.101 -server -bootstrap-expect=1 >> nohup.out &