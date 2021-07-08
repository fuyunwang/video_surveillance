#coding=utf-8

from fdfs_client.client import get_tracker_conf, Fdfs_client
def main():
    tracker_conf = get_tracker_conf('fast_client.conf')
    client = Fdfs_client(tracker_conf)

    #文件上传，结果返回：{'Group name': b'group1', 'Remote file_id': b'group1/M00/00/00/wKgf3F5MAe2AV_23AAAADL_GVeU370.txt', 'Status': 'Upload successed.', 'Local file name': 'test.txt', 'Uploaded size': '12B', 'Storage IP': b'192.168.31.220'}
    result = client.upload_by_filename('D:\\baiduyun_0514\\safety\\safety.mp4')
    print(result)

    # 文件下载，结果返回：{'Remote file_id': b'group1/M00/00/00/wKgf3F5MAe2AV_23AAAADL_GVeU370.txt', 'Content': 't.txt', 'Download size': '12B', 'Storage IP': b'192.168.31.220'}
    # result = client.download_to_file('t.txt', b'group1/M00/00/00/wKgf3F5MAe2AV_23AAAADL_GVeU370.txt')

    #文件删除，结果返回：('Delete file successed.', b'group1/M00/00/00/wKgf3F5MAe2AV_23AAAADL_GVeU370.txt', b'192.168.31.220')
    # result = client.delete_file(b'group1/M00/00/00/wKgf3F5MAe2AV_23AAAADL_GVeU370.txt')

    #列出所有的group信息
    result = client.list_all_groups()
    print(result)
    #列出同一组内的storage servers信息
    # result = client.list_servers(b'group1')
    # print(result)

if __name__ == '__main__':
    main()
