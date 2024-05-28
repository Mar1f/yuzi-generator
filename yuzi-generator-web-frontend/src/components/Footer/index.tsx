import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = '阿祥哥qqe';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'Ant Design',
          title: '吉师纯情阿祥哥',
          href: 'https://www.douyin.com/user/MS4wLjABAAAAW3O6b3hutefE8MvtIJ0DoQgcXnzvPnK8e72dDLeVe9w',
          blankTarget: true,
        },
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined /> Mar1f
            </>
          ),
          href: 'https://github.com/Mar1f',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
